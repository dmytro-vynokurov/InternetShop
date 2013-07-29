package dto.cart;

import dto.service.Util;
import ejb.CartEJB;
import entities.Item;
import entities.ItemOrder;
import entities.Order;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.Serializable;

import static dto.service.NavigationShop.REGISTER_ORDER_PAGE;

/**
 * User: Dmitry
 * Date: 7/19/13
 * Time: 8:25 AM
 */
@ManagedBean(name = "cartDTO")
@SessionScoped
public class CartDTO implements Serializable {

    @EJB
    CartEJB cartEJB;
    @ManagedProperty(value = "#{cartOrderDetailsDTO}")
    private CartOrderDetailsDTO cartOrderDetailsDTO;
    ItemOrder selectedEntry;

    public void addItem(Item item) {
        cartEJB.addItem(item);
        Util.createMessage(cartEJB.findEntry(item).getQuantity()+" "+item.getItemName()+ " in the cart");
    }

    public String confirmOrder() throws IOException {
        System.out.println("When moving to confirming order\t"+cartEJB.getOrder().getItemOrders());
        cartOrderDetailsDTO.setCartEJB(cartEJB);
        return REGISTER_ORDER_PAGE;
    }

    public double getTotalPrice() {
        return cartEJB.getTotalPrice();
    }

    public CartModel getCartModel() {
        return new CartModel(cartEJB.getOrder().getItemOrders());
    }

    public Order getCurrentOrder() {
        return cartEJB.getOrder();
    }

    public void setCurrentOrder(Order currentOrder) {
        System.out.println("Setting cartEJB order from cartDTO - itemOrders:"+
                currentOrder.getItemOrders());
        cartEJB.setOrder(currentOrder);
    }

    public ItemOrder getSelectedEntry() {
        return selectedEntry;
    }

    public void setSelectedEntry(ItemOrder selectedEntry) {
        this.selectedEntry = selectedEntry;
    }

    public void setCartOrderDetailsDTO(CartOrderDetailsDTO cartOrderDetailsDTO) {
        this.cartOrderDetailsDTO = cartOrderDetailsDTO;
    }
}


