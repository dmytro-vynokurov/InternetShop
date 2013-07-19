package dto.cart;

import dao.OrderDAO;
import entities.Item;
import entities.ItemOrder;
import entities.Order;
import entities.dictionaries.DeliveryType;
import entities.dictionaries.PaymentType;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 * User: Dmitry
 * Date: 7/19/13
 * Time: 8:25 AM
 */
@ManagedBean(name="cartDTO")
@SessionScoped
public class CartDTO {

    @EJB
    OrderDAO orderDAO;
    Order currentOrder;
    ItemOrder selectedEntry;

    public void addItem(Item item){
        ItemOrder entry = findEntry(item);
        if(entry!=null){
            entry.setQuantity(entry.getQuantity()+1);
        }else{
            entry=new ItemOrder();
            entry.setItem(item);
            entry.setOrder(currentOrder);
            entry.setQuantity(1);
            currentOrder.getItems().add(item);
        }
    }

    private ItemOrder findEntry(Item item){
        for (ItemOrder entry : currentOrder.getItemOrders()) {
            if(item.equals(entry.getItem()))return entry;
        }
        return null;
    }

    public void registerOrder(){
        orderDAO.create(currentOrder);
    }

    public double getTotalPrice(){
        double sum=0;
        for (ItemOrder entry : currentOrder.getItemOrders()) {
            sum+=entry.getQuantity()*entry.getItem().getPrice();
        }
        return sum;
    }

    public CartModel getCartModel(){
        return new CartModel(currentOrder.getItemOrders());
    }

    public SelectItem[] getPaymentTypes() {
        SelectItem[] items = new SelectItem[PaymentType.values().length];
        int i = 0;
        for (PaymentType g : PaymentType.values()) {
            items[i++] = new SelectItem(g, g.getText());
        }
        return items;
    }

    public SelectItem[] getDeliveryTypes() {
        SelectItem[] items = new SelectItem[DeliveryType.values().length];
        int i = 0;
        for (PaymentType g : PaymentType.values()) {
            items[i++] = new SelectItem(g, g.getText());
        }
        return items;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public ItemOrder getSelectedEntry() {
        return selectedEntry;
    }

    public void setSelectedEntry(ItemOrder selectedEntry) {
        this.selectedEntry = selectedEntry;
    }
}
