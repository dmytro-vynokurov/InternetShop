package dao;

import entities.ItemOrder;
import entities.Order;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/25/13
 * Time: 4:11 PM
 */
@Stateless
public class ItemOrderDAO extends GenericDAO<ItemOrder> {

    public List<ItemOrder> findItemOrdersOfOrder(final Order order) {
        return executeQuery(new QueryBuilder() {
            @Override
            public TypedQuery<ItemOrder> buildQuery() {
                TypedQuery<ItemOrder> query = em.createQuery("SELECT io FROM ItemOrder io WHERE io.order=:order", ItemOrder.class);
                return query.setParameter("order", order);
            }
        });
    }

    @Override
    protected Class<ItemOrder> entityClass() {
        return ItemOrder.class;
    }
}
