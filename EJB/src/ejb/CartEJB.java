package ejb;

import dao.OrderDAO;
import entities.Item;
import entities.ItemOrder;
import entities.Order;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.ArrayList;

/**
 * User: Dmitry
 * Date: 7/20/13
 * Time: 11:03 PM
 */
@Stateful
public class CartEJB {
    @EJB
    OrderDAO orderDAO;

    private Order order;

    public CartEJB() {
        order = new Order();
        order.setItemOrders(new ArrayList<ItemOrder>());
    }

    public CartEJB(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void addItem(Item item) {
        ItemOrder entry = findEntry(item);
        if (entry != null) {
            entry.setQuantity(entry.getQuantity() + 1);
        } else {
            entry = new ItemOrder();
            entry.setItem(item);
            entry.setOrder(order);
            entry.setQuantity(1);
            order.getItemOrders().add(entry);
        }
    }

    public void setQuantity(Item item, int quantity) {
        ItemOrder entry = findEntry(item);
        if (entry == null) {
            entry = new ItemOrder();
            entry.setItem(item);
            entry.setOrder(order);
            entry.setQuantity(quantity);
        } else {
            entry.setQuantity(quantity);
        }
    }

    public int getQuantity(Item item) {
        ItemOrder entry = findEntry(item);
        if (entry == null) return 0;
        else return entry.getQuantity();
    }

    private ItemOrder findEntry(Item item) {
        for (ItemOrder entry : order.getItemOrders()) {
            if (item.equals(entry.getItem())) return entry;
        }
        return null;
    }

    public void registerOrder() {
        System.out.println("Registering order:\t"+order);
        orderDAO.create(order);
        this.order=null;
    }

    public double getTotalPrice() {
        double sum = 0;
        for (ItemOrder entry : order.getItemOrders()) {
            sum += entry.getQuantity() * entry.getItem().getPrice();
        }
        return sum;
    }
}
