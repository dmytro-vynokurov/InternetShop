package dto.order;

import dao.OrderDAO;
import entities.Order;
import entities.dictionaries.DeliveryType;
import entities.dictionaries.PaymentType;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import java.io.Serializable;

/**
 * User: Dmitry
 * Date: 7/19/13
 * Time: 2:22 AM
 */
@ManagedBean(name = "orderDTO")
@SessionScoped
public class OrderDTO implements Serializable {

    @EJB
    OrderDAO orderDAO;
    Order selectedOrder;


    public void registerOrder() {
        orderDAO.create(selectedOrder);
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

    public Order getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(Order selectedOrder) {
        this.selectedOrder = selectedOrder;
    }
}
