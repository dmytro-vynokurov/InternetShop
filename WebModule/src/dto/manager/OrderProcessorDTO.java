package dto.manager;

import dao.ItemOrderDAO;
import dao.OrderDAO;
import dto.cart.CartModel;
import ejb.CartEJB;
import entities.Order;
import entities.dictionaries.OrderStatus;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
    private CartEJB cartEJB;
    @EJB
    private OrderDAO orderDAO;
    @EJB
    private ItemOrderDAO itemOrderDAO;
    @ManagedProperty(value = "#{orderPickerDTO}")
    private OrderPickerDTO orderPickerDTO;

    @PostConstruct
    public void initializeCartEJB(){
        cartEJB.setOrder(orderPickerDTO.getSelectedOrder());
    }

    public void confirmOrder() throws IOException {
        Order order=cartEJB.getOrder();
        order.setOrderStatus(OrderStatus.WF_PAYMENT);
        System.out.println("Order confirmed: "+order);
        orderDAO.update(order);
        navigateTo(LIST_OF_ORDERS_PAGE);
    }

    public void discardOrder() throws IOException {
        Order order=cartEJB.getOrder();
        order.setOrderStatus(OrderStatus.CANCELED);
        System.out.println("Order discarded: "+order);
        orderDAO.update(order);
        navigateTo(LIST_OF_ORDERS_PAGE);
    }

    public void backToOrders() throws IOException {
        navigateTo(LIST_OF_ORDERS_PAGE);
    }

    public double getTotalPrice() {
        return cartEJB.getTotalPrice();
    }

    public CartModel getCartModel() {
        Order order=cartEJB.getOrder();
        order=orderDAO.update(order);
        cartEJB.setOrder(order);
        return new CartModel(order.getItemOrders());
    }

    public Order getOrder(){
        return cartEJB.getOrder();
    }

    public void setOreder(Order order){
        cartEJB.setOrder(order);
    }

    public void setOrderPickerDTO(OrderPickerDTO orderPickerDTO) {
        this.orderPickerDTO = orderPickerDTO;
    }
}
