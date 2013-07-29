package ejb;

import dao.OrderDAO;
import entities.Item;
import entities.ItemOrder;
import entities.Order;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

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
        initializeOrder();
    }

    private void initializeOrder() {
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
        System.out.println("cartEJB adding item"+order.getItemOrders());
        System.out.println("cartEJB: "+this.toString());
        ItemOrder entry = findEntry(item);
        if (entry != null) {
            entry.setQuantity(entry.getQuantity() + 1);
        } else {
            entry=new ItemOrder(item,order,1,item.getPrice());
            order.getItemOrders().add(entry);
        }
    }

    public void setQuantity(Item item, int quantity) {
        System.out.println("cartEJB setting quantity"+order.getItemOrders());
        ItemOrder entry = findEntry(item);
        if (entry == null) {
            entry=new ItemOrder(item,order,quantity,item.getPrice());
            order.getItemOrders().add(entry);
        } else {
            entry.setQuantity(quantity);
        }
    }

    public int getQuantity(Item item) {
        System.out.println("cartEJB getting quantity");
        ItemOrder entry = findEntry(item);
        if (entry == null) return 0;
        else return entry.getQuantity();
    }

    public ItemOrder findEntry(Item item) {
        for (ItemOrder entry : order.getItemOrders()) {
            if (item.equals(entry.getItem())) return entry;
        }
        return null;
    }

    public void registerOrder() {
        System.out.println("Registering order:\t" + order);
        System.out.println("In CartEJB items: "+order.getItemOrders());
        System.out.println("cartEJB: "+this.toString());

        List<ItemOrder> entries = order.getItemOrders();
        for(ItemOrder entry:entries){
            if(entry.getQuantity()==0) entries.remove(entry);
        }

        orderDAO.create(order);
        initializeOrder();
    }

    public double getTotalPrice() {
        double sum = 0;
        for (ItemOrder entry : order.getItemOrders()) {
            sum += entry.getQuantity() * entry.getPrice();
        }
        return sum;
    }
}
