package dao;

import entities.Order;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/25/13
 * Time: 4:11 PM
 */
@Stateless
public class OrderDAO extends GenericDAO<Order> {

    private final static String SORTED_BY_SUM_QUERY=
                    "select o.id_order,o.user_name,o.user_phone,o.\"Comment\"," +
                    "o.delivery_type,o.payment_type,o.order_status," +
                            "sum(io.quantity*i.price) as tp\n" +
                    "from (\"ORDER\" o join item_order io on o.id_order=io.id_order)\n" +
                    "     join Item i on i.id_item=io.id_item\n" +
                    "group by o.id_order\n" +
                    "order by tp";

    private final static String TOTAL_COST_QUERY=
                    "select sum(io.quantity*i.price) as tp\n" +
                    "from (\"ORDER\" o join item_order io on o.id_order=io.id_order)\n" +
                    "     join Item i on i.id_item=io.id_item\n" +
                    "where o.id_order=:orderID";

    private final static String SORTED_BY_SUM_IN_RANGE=
                    "select o.id_order\n" +
                    "from\n" +
                    "           (select o.id_order,o.user_name,o.user_phone,o.\"Comment\",\n" +
                            "                    \"o.delivery_type,o.payment_type,o.order_status,\" +\n" +
                            "                    dense_rank() over (order by sum(io.quantity*i.price)) rnk\n" +
                    "            from (\"ORDER\" o join item_order io on o.id_order=io.id_order)\n" +
                    "                 join Item i on i.id_item=io.id_item\n" +
                    "            group by o.id_order)o\n" +
                    "where rnk>:min_val and rnk<:max_val\n" +
                    "order by rnk";

    public List<Order> findSortedBySum() {
        return executeQuery(new QueryBuilder() {
            @Override
            public Query buildQuery() {
                return em.createNativeQuery(SORTED_BY_SUM_QUERY, Order.class);
            }
        });
    }

    public double getTotalCost(Order order){
        Query query=em.createNativeQuery(TOTAL_COST_QUERY,Order.class);
        query.setParameter("orderID",order.getIdOrder());
        return (Double) query.getSingleResult();
    }

    public List<Order> findSortedBySumInRange(int start, int finish,boolean descending){
        String sortOrder;
        if(descending) sortOrder="desc";
        else sortOrder="asc";
        Query query = em.createNativeQuery(SORTED_BY_SUM_IN_RANGE+" "+sortOrder,Query.class);
        query.setParameter("min_val",start);
        query.setParameter("max_val",finish);
        return (List<Order>)query.getResultList();
    }

    @Override
    protected Class<Order> entityClass() {
        return Order.class;
    }
}
