package dao;

import entities.ItemOrder;

import javax.ejb.Stateless;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/25/13
 * Time: 4:11 PM
 */
@Stateless
public class ItemOrderDAO extends GenericDAO<ItemOrder> {
    @Override
    protected Class<ItemOrder> entityClass() {
        return ItemOrder.class;
    }
}
