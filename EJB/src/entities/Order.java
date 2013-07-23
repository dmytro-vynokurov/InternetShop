package entities;

import entities.dictionaries.DeliveryType;
import entities.dictionaries.OrderStatus;
import entities.dictionaries.PaymentType;

import javax.persistence.*;
import java.util.List;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/20/13
 * Time: 5:19 PM
 */
@Entity
@Table(name = "ORDER1", catalog = "", schema = "INTERNETSHOP")
public final class Order {
    @Column(name = "ID_ORDER", nullable = false, insertable = true, updatable = true, length = 6, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_Order")
    @SequenceGenerator(name = "S_Order", sequenceName = "S_ORDER", allocationSize = 1)
    private int idOrder;
    @Column(name = "USER_NAME", nullable = true, insertable = true, updatable = true, length = 40, precision = 0)
    @Basic
    private String userName;
    @Column(name = "USER_PHONE", nullable = true, insertable = true, updatable = true, length = 15, precision = 0)
    @Basic
    private String userPhone;
    @Column(name = "USER_EMAIL", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    private String userEmail;
    @Column(name = "ORDER_COMMENT", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
    @Basic
    private String comment;
    @Column(name = "DELIVERY_TYPE", nullable = false, insertable = true, updatable = true, length = 30, precision = 0)
    @Basic
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;
    @Column(name = "PAYMENT_TYPE", nullable = false, insertable = true, updatable = true, length = 30, precision = 0)
    @Basic
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Column(name = "ORDER_STATUS", nullable = false, insertable = true, updatable = true, length = 30, precision = 0)
    @Basic
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<ItemOrder> itemOrders;
    @JoinTable(name = "ITEM_ORDER", catalog = "", schema = "INTERNETSHOP", joinColumns = @JoinColumn(name = "ID_ORDER", referencedColumnName = "ID_ORDER", nullable = false), inverseJoinColumns = @JoinColumn(name = "ID_ITEM", referencedColumnName = "ID_ITEM", nullable = false))
    @ManyToMany
    private List<Item> items;

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    final public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (idOrder != order.idOrder) return false;
        if (comment != null ? !comment.equals(order.comment) : order.comment != null) return false;
        if (deliveryType != null ? !deliveryType.equals(order.deliveryType) : order.deliveryType != null) return false;
        if (orderStatus != null ? !orderStatus.equals(order.orderStatus) : order.orderStatus != null) return false;
        if (paymentType != null ? !paymentType.equals(order.paymentType) : order.paymentType != null) return false;
        if (userEmail != null ? !userEmail.equals(order.userEmail) : order.userEmail != null) return false;
        if (userName != null ? !userName.equals(order.userName) : order.userName != null) return false;
        if (userPhone != null ? !userPhone.equals(order.userPhone) : order.userPhone != null) return false;

        return true;
    }

    @Override
    final public int hashCode() {
        int result = idOrder;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userPhone != null ? userPhone.hashCode() : 0);
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (deliveryType != null ? deliveryType.hashCode() : 0);
        result = 31 * result + (paymentType != null ? paymentType.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", comment='" + comment + '\'' +
                ", deliveryType=" + deliveryType +
                ", paymentType=" + paymentType +
                ", orderStatus=" + orderStatus +
                '}';
    }

    public List<ItemOrder> getItemOrders() {
        return itemOrders;
    }

    public void setItemOrders(List<ItemOrder> itemOrders) {
        this.itemOrders = itemOrders;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
