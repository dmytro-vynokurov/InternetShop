package dao;

import entities.ItemOrder;
import entities.Order;

import javax.ejb.Stateless;
import java.util.List;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/25/13
 * Time: 4:11 PM
 */
@Stateless
public class ItemOrderDAO extends GenericDAO<ItemOrder> {
    private static final String FIND_ITEM_ORDERS_OF_ORDER = "findItemOrdersOfOrder";

    public List<ItemOrder> findItemOrdersOfOrder(final Order order) {
        return em.createNamedQuery(FIND_ITEM_ORDERS_OF_ORDER, ItemOrder.class)
                .setParameter("order", order)
                .getResultList();
    }

    @Override
    protected Class<ItemOrder> entityClass() {
        return ItemOrder.class;
    }
}
