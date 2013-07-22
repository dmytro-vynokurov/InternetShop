package dto.manager;

import dao.ItemOrderDAO;
import dao.OrderDAO;
import dto.cart.CartModel;
import entities.Order;
import entities.dictionaries.OrderStatus;
import org.primefaces.model.LazyDataModel;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.Serializable;

import static dto.service.NavigationManager.LIST_OF_ORDERS_PAGE;
import static dto.service.NavigationManager.ORDER_PROCESSING_PAGE;
import static dto.service.Util.navigateTo;

/**
 * User: Dmitry
 * Date: 7/22/13
 * Time: 2:25 AM
 */
@ManagedBean(name = "orderPickerDTO")
@SessionScoped
public class OrderPickerDTO implements Serializable {
    private static final int DEFAULT_PAGE_SIZE = 20;

    @EJB
    OrderDAO orderDAO;
    @EJB
    ItemOrderDAO itemOrderDAO;
    Order selectedOrder;

    public void confirmOrder() throws IOException {
        selectedOrder.setOrderStatus(OrderStatus.WF_PAYMENT);
        navigateTo(LIST_OF_ORDERS_PAGE);
    }

    public void discardOrder() throws IOException {
        selectedOrder.setOrderStatus(OrderStatus.CANCELED);
        navigateTo(LIST_OF_ORDERS_PAGE);
    }

    public void backToOrders() throws IOException {
        selectedOrder=null;
        navigateTo(LIST_OF_ORDERS_PAGE);
    }

    public void processOrder() throws IOException {
        navigateTo(ORDER_PROCESSING_PAGE);
    }

    public double getTotalPrice(){
        return totalOrderPrice(selectedOrder);
    }

    public double totalOrderPrice(Order order) {
        return orderDAO.getTotalCost(order);
    }

    public CartModel getCartModel(){
        selectedOrder.setItemOrders(itemOrderDAO.findItemOrdersOfOrder(selectedOrder));
        return new CartModel(selectedOrder.getItemOrders());
    }

    public LazyDataModel<Order> getOrderPickerModel() {
        OrderPickerModel model = new OrderPickerModel(orderDAO);
        model.setRowCount(orderDAO.count().intValue());
        fixPageSize(model);
        return model;
    }

    private void fixPageSize(OrderPickerModel model) {
        int pageSize = model.getPageSize();
        if (pageSize < 1) model.setPageSize(DEFAULT_PAGE_SIZE);
    }

    public Order getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(Order selectedOrder) {
        this.selectedOrder = selectedOrder;
    }
}
