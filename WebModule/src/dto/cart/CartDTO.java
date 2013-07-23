package dto.cart;

import dto.service.Util;
import ejb.CartEJB;
import entities.Item;
import entities.ItemOrder;
import entities.Order;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.Serializable;

import static dto.service.NavigationShop.REGISTER_ORDER_PAGE;
import static dto.service.Util.navigateTo;

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
    ItemOrder selectedEntry;

    public void addItem(Item item) {
        cartEJB.addItem(item);
        Util.createMessage(item.getItemName()+" added to the cart");
    }

    public void confirmOrder() throws IOException {
        navigateTo(REGISTER_ORDER_PAGE);
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
        cartEJB.setOrder(currentOrder);
    }

    public ItemOrder getSelectedEntry() {
        return selectedEntry;
    }

    public void setSelectedEntry(ItemOrder selectedEntry) {
        this.selectedEntry = selectedEntry;
    }
}
