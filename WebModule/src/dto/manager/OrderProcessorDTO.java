package dto.manager;

import dao.OrderDAO;
import dto.cart.CartModel;
import ejb.CartEJB;
import entities.dictionaries.OrderStatus;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.IOException;
import java.io.Serializable;

import static dto.service.NavigationManager.LIST_OF_ORDERS_PAGE;
import static dto.service.Util.navigateTo;

/**
 * User: Dmitry
 * Date: 7/22/13
 * Time: 8:36 AM
 */
@ManagedBean
@ViewScoped
public class OrderProcessorDTO implements Serializable {
    @EJB
    CartEJB cartEJB;
    @EJB
    OrderDAO orderDAO;

    public void confirmOrder() throws IOException {
        cartEJB.getOrder().setOrderStatus(OrderStatus.WF_PAYMENT);
        orderDAO.update(cartEJB.getOrder());
        navigateTo(LIST_OF_ORDERS_PAGE);
    }

    public void discardOrder() throws IOException {
        cartEJB.getOrder().setOrderStatus(OrderStatus.CANCELED);
        orderDAO.update(cartEJB.getOrder());
        navigateTo(LIST_OF_ORDERS_PAGE);
    }

    public void backToOrders() throws IOException {
        navigateTo(LIST_OF_ORDERS_PAGE);
    }

    public double getTotalPrice(){
        return cartEJB.getTotalPrice();
    }

    public CartModel getCartModel(){
        return new CartModel(cartEJB.getOrder().getItemOrders());
    }
}
