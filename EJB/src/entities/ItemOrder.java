package entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/20/13
 * Time: 5:19 PM
 */

@Entity
@Table(name = "ITEM_ORDER", schema = "INTERNETSHOP", catalog = "")
@NamedQuery(name="findItemOrdersOfOrder",query = "SELECT io FROM ItemOrder io WHERE io.order=:order")
public class ItemOrder implements Serializable {
    @Column(name = "QUANTITY", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    @Basic
    private int quantity;
    @Id
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_ITEM", referencedColumnName = "ID_ITEM", nullable = false)
    private Item item;
    @Id
    @ManyToOne
    @JoinColumn(name = "ID_ORDER", referencedColumnName = "ID_ORDER", nullable = false)
    private Order order;
    @Column(name="PRICE",nullable = false,length = 8,precision = 2)
    @Basic
    private double price;

    public ItemOrder() {
    }

    public ItemOrder(Item item, Order order,int quantity, double price) {
        this.quantity = quantity;
        this.item = item;
        this.order = order;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemOrder)) return false;

        ItemOrder itemOrder = (ItemOrder) o;

        if (Double.compare(itemOrder.price, price) != 0) return false;
        if (quantity != itemOrder.quantity) return false;
        if (!item.equals(itemOrder.item)) return false;
        if (!order.equals(itemOrder.order)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = quantity;
        result = 31 * result + item.hashCode();
        result = 31 * result + order.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ItemOrder{" +
                "quantity=" + quantity +
                ", price=" + price +
                ", item=" + item.getItemName() +
                ", order=" + order.getIdOrder() +
                '}';
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
