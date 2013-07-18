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
public class ItemOrder implements Serializable {
    @Column(name = "QUANTITY", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    @Basic
    private int quantity;
    @Id
    @ManyToOne
    @JoinColumn(name = "ID_ITEM", referencedColumnName = "ID_ITEM", nullable = false)
    private Item item;
    @Id
    @ManyToOne
    @JoinColumn(name = "ID_ORDER", referencedColumnName = "ID_ORDER", nullable = false)
    private Order order;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemOrder)) return false;

        ItemOrder itemOrder = (ItemOrder) o;

        if (quantity != itemOrder.quantity) return false;
        if (item != null ? !item.equals(itemOrder.item) : itemOrder.item != null) return false;
        if (order != null ? !order.equals(itemOrder.order) : itemOrder.order != null) return false;

        return true;
    }

    @Override
    public final int hashCode() {
        int result = 31 + quantity;
        result = 31 * result + (item != null ? item.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ItemOrder{" +
                "quantity=" + quantity +
                ", item=" + item +
                ", order=" + order +
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
