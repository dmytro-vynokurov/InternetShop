package dto.cart;

import dto.service.Navigator;
import dto.service.Util;
import ejb.CartEJB;
import entities.Order;
import entities.dictionaries.DeliveryType;
import entities.dictionaries.OrderStatus;
import entities.dictionaries.PaymentType;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import java.io.IOException;
import java.io.Serializable;

import static dto.service.Util.*;

/**
 * User: Dmitry
 * Date: 7/22/13
 * Time: 1:40 AM
 */
@ManagedBean(name= "cartOrderDetailsDTO")
public class CartOrderDetailsDTO implements Serializable {

    @EJB
    private CartEJB cartEJB;

    private PaymentType paymentType;
    private DeliveryType deliveryType;
    private String name;
    private String phone;
    private String email;
    private String comment;

    public void registerOrder() throws IOException {
        System.out.println("In request order");
        Order order = cartEJB.getOrder();
        order.setPaymentType(paymentType);
        order.setDeliveryType(deliveryType);
        order.setUserName(name);
        order.setUserPhone(phone);
        order.setUserEmail(email);
        order.setComment(comment);
        order.setOrderStatus(OrderStatus.WF_PROCESSING);

        System.out.println("Order: "+order);

        cartEJB.registerOrder();

        Navigator.moveToIndex();
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
