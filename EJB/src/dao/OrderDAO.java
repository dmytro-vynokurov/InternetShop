package dao;

import entities.Order;
import entities.dictionaries.OrderStatus;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
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
            "select o.id_order as ID_ORDER,o.user_name as USER_NAME," +
                    "o.user_phone as USER_PHONE,o.user_email as USER_EMAIL,o.order_comment as ORDER_COMMENT,\n" +
                    "  o.delivery_type as DELIVERY_TYPE,o.payment_type as PAYMENT_TYPE,o.order_status as ORDER_STATUS\n" +
                    "from\n" +
                    "  (    select o.id_order,o.user_name,o.user_phone,o.user_email,o.order_comment,\n" +
                    "        o.delivery_type,o.payment_type,o.order_status,\n" +
                    "        dense_rank() over (partition by o.id_order order by sum(io.quantity*i.price)) rnk\n" +
                    "      from (ORDER1 o left join item_order io on o.id_order=io.id_order)\n" +
                    "        left join Item i on i.id_item=io.id_item\n" +
                    "      where o.order_status='WF_PROCESSING'\n" +
                    "      group by o.id_order,o.user_name,o.user_phone,o.user_email,o.order_comment,\n" +
                    "        o.delivery_type,o.payment_type,o.order_status)o\n" +
                    "order by rnk";

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
                    "      from (ORDER1 o left join item_order io on o.id_order=io.id_order)\n" +
                    "        left join Item i on i.id_item=io.id_item\n" +
                    "      where o.order_status='WF_PROCESSING'\n" +
                    "      group by o.id_order,o.user_name,o.user_phone,o.user_email,o.order_comment,\n" +
                    "        o.delivery_type,o.payment_type,o.order_status)o\n" +
                    "where rnk>:min_val and rnk<:max_val\n" +
                    "order by rnk";

    private final static String FIND_BY_ID =
            "select o.id_order as ID_ORDER,o.user_name as USER_NAME," +
                    "o.user_phone as USER_PHONE,o.user_email as USER_EMAIL,o.order_comment as ORDER_COMMENT,\n" +
                    "  o.delivery_type as DELIVERY_TYPE,o.payment_type as PAYMENT_TYPE,o.order_status as ORDER_STATUS\n" +
                    "from ORDER1 o\n" +
                    "where ID_ORDER=:idOrder";

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
        BigDecimal result=(BigDecimal) query.getSingleResult();
        return (result==null)?0:result.doubleValue();
    }

    @Override
    public Order find(Object id) {
        Query query = em.createNativeQuery(FIND_BY_ID, Order.class)
                .setParameter("idOrder", id);
        return (Order) query.getSingleResult();
    }

    public List<Order> findSortedBySumWaitingForProcessingInRange(int start, int finish, boolean descending) {
        System.out.println("Running query from " + start + " to " + finish);
        String sortOrder;
        if (descending) sortOrder = "desc";
        else sortOrder = "asc";
        Query query = em.createNativeQuery(SORTED_BY_SUM_WAITING_FOR_PROCESSING_IN_RANGE + " " + sortOrder, Order.class);
        query.setParameter("min_val", start - 1);
        query.setParameter("max_val", finish);
        List<Order> result = (List<Order>) query.getResultList();
        System.out.println("Result: " + result);
        return result;
    }

    @Override
    protected Class<Order> entityClass() {
        return Order.class;
    }
}
