package dao;

import entities.Order;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/25/13
 * Time: 4:11 PM
 */
@Stateless
public class OrderDAO extends GenericDAO<Order> {

    private final static String SORTED_BY_SUM_WAITING_FOR_PROCESSING_QUERY =
            "select o.id_order,o.user_name,o.user_phone,o.order_comment," +
                    "o.delivery_type,o.payment_type,o.order_status," +
                    "sum(io.quantity*i.price) as tp\n" +
                    "from (ORDER1 o join item_order io on o.id_order=io.id_order)\n" +
                    "     join Item i on i.id_item=io.id_item\n" +
                    "where o.order_status='WAITING_FOR_PROCESSING'\n" +
                    "group by o.id_order\n" +
                    "order by tp";

    private final static String TOTAL_COST_QUERY =
            "select sum(io.quantity*i.price) as DV\n" +
                    "from (ORDER1 o join item_order io on o.id_order=io.id_order)\n" +
                    "     join Item i on i.id_item=io.id_item\n" +
                    "where o.id_order=:orderID";

    private final static String SORTED_BY_SUM_WAITING_FOR_PROCESSING_IN_RANGE =
            "select o.id_order as ID_ORDER,o.user_name as USER_NAME," +
                    "o.user_phone as USER_PHONE,o.user_email as USER_EMAIL,o.order_comment as ORDER_COMMENT,\n" +
                    "  o.delivery_type as DELIVERY_TYPE,o.payment_type as PAYMENT_TYPE,o.order_status as ORDER_STATUS\n" +
                    "from\n" +
                    "  (    select o.id_order,o.user_name,o.user_phone,o.user_email,o.order_comment,\n" +
                    "        o.delivery_type,o.payment_type,o.order_status,\n" +
                    "        dense_rank() over (partition by o.id_order order by sum(io.quantity*i.price)) rnk\n" +
                    "      from (ORDER1 o join item_order io on o.id_order=io.id_order)\n" +
                    "        join Item i on i.id_item=io.id_item\n" +
                    "      where o.order_status='WF_PROCESSING'\n" +
                    "      group by o.id_order,o.user_name,o.user_phone,o.user_email,o.order_comment,\n" +
                    "        o.delivery_type,o.payment_type,o.order_status)o\n" +
                    "where rnk>:min_val and rnk<:max_val\n" +
                    "order by rnk";

    public List<Order> findSortedBySumWaitingForProcessing() {
        return executeQuery(new QueryBuilder() {
            @Override
            public Query buildQuery() {
                return em.createNativeQuery(SORTED_BY_SUM_WAITING_FOR_PROCESSING_QUERY, Order.class);
            }
        });
    }

    public double getTotalCost(Order order) {
        Query query = em.createNativeQuery(TOTAL_COST_QUERY);
        query.setParameter("orderID", order.getIdOrder());
        return ((BigDecimal) query.getSingleResult()).doubleValue();
    }

    public List<Order> findSortedBySumWaitingForProcessingInRange(int start, int finish, boolean descending) {
        System.out.println("Running query from "+start+" to "+finish);
        String sortOrder;
        if (descending) sortOrder = "desc";
        else sortOrder = "asc";
        Query query = em.createNativeQuery(SORTED_BY_SUM_WAITING_FOR_PROCESSING_IN_RANGE + " " + sortOrder, Order.class);
        query.setParameter("min_val", start-1);
        query.setParameter("max_val", finish);
        List<Order> result = (List<Order>) query.getResultList();
        System.out.println("Result: " + result);
        return result;
    }

    @Override
    public Order find(final Object idOrder){
        return executeQuery(new QueryBuilder() {
            @Override
            public TypedQuery<Order> buildQuery() {
                TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o WHERE o.idOrder=:id", Order.class);
                return query.setParameter("id", (Integer)idOrder);
            }
        }).get(0);
    }

    @Override
    protected Class<Order> entityClass() {
        return Order.class;
    }
}
