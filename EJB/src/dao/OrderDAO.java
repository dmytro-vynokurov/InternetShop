package dao;

import entities.Order;

import javax.ejb.Stateless;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/25/13
 * Time: 4:11 PM
 */
@Stateless
public class OrderDAO extends GenericDAO<Order> {
    @Override
    protected Class<Order> entityClass() {
        return Order.class;
    }
}
