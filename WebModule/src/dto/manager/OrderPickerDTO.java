package dto.manager;

import dao.ItemOrderDAO;
import dao.OrderDAO;
import dto.cart.CartModel;
import dto.order.OrderModel;
import ejb.CartEJB;
import entities.Order;
import entities.dictionaries.OrderStatus;
import org.primefaces.model.LazyDataModel;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.naming.NamingException;
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
    private OrderDAO orderDAO;
    private Order selectedOrder;

    public void processOrder() throws IOException {
        navigateTo(ORDER_PROCESSING_PAGE);
    }

    public double getTotalPrice() {
        return totalOrderPrice(selectedOrder);
    }

    public double totalOrderPrice(Order order) {
        return orderDAO.getTotalCost(order);
    }

    public DataModel<Order> getDataModel(){
        return new OrderModel(orderDAO.findSortedBySumWaitingForProcessing());
    }

    public LazyDataModel<Order> getOrderPickerModel() throws NamingException {
        OrderLazyModel model = new OrderLazyModel();
        model.setRowCount(orderDAO.count().intValue());
        fixPageSize(model);
        return model;
    }

    private void fixPageSize(OrderLazyModel model) {
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
