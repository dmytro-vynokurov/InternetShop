package entities;

import entities.dictionaries.Color;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/20/13
 * Time: 5:19 PM
 */
@Entity
@Table(name = "ITEM", catalog = "", schema = "INTERNETSHOP")
public final class Item implements Serializable {
    @Column(name = "ID_ITEM", nullable = false, insertable = true, updatable = true, length = 6, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_Item")
    @SequenceGenerator(name = "S_Item", sequenceName = "S_ITEM", allocationSize = 1)
    private int idItem;
    @Column(name = "ITEM_NAME", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    private String itemName;
    @Column(name = "MANUFACTURER", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    private String manufacturer;
    @Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 4000, precision = 0)
    @Basic(fetch = FetchType.EAGER)
    @Lob
    private String description;
    @Column(name = "QUANTITY", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    @Basic
    private int quantity;
    @Column(name = "PRICE", nullable = false, insertable = true, updatable = true, length = 8, precision = 2)
    @Basic
    private Double price;
    @Column(name = "LENGTH", nullable = true, insertable = true, updatable = true, length = 22, precision = 0)
    @Basic
    private Integer length;
    @Column(name = "WIDTH", nullable = true, insertable = true, updatable = true, length = 22, precision = 0)
    @Basic
    private Integer width;
    @Column(name = "HEIGHT", nullable = true, insertable = true, updatable = true, length = 22, precision = 0)
    @Basic
    private Integer height;
    @Column(name = "WEIGHT", nullable = true, insertable = true, updatable = true, length = 22, precision = 0)
    @Basic
    private Integer weight;
    @Column(name = "COLOR", nullable = true, insertable = true, updatable = true, length = 15, precision = 0)
    @Basic
    @Enumerated(EnumType.STRING)
    private Color color;

    @OneToMany(mappedBy = "item")
    private List<Characteristic> characteristics;
    @OneToMany(mappedBy = "item")
    private List<Comment> comments;
    @ManyToOne
    @JoinColumn(name = "ID_CATEGORY", referencedColumnName = "ID_CATEGORY", nullable = false)
    private Category category;
    @OneToMany(mappedBy = "item")
    private List<ItemOrder> itemOrders;
    @OneToMany(mappedBy = "item")
    private List<Photo> photos;
    @OneToMany(mappedBy = "item")
    private List<Video> videos;
    @ManyToMany(mappedBy = "items")
    private List<Order> orders;

    public Item() {
        characteristics = null;
        comments = null;
        itemOrders = null;
        photos = null;
        videos = null;
        orders = null;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (idItem != item.idItem) return false;
        if (quantity != item.quantity) return false;
        if (color != null ? !color.equals(item.color) : item.color != null) return false;
        if (description != null ? !description.equals(item.description) : item.description != null) return false;
        if (height != null ? !height.equals(item.height) : item.height != null) return false;
        if (itemName != null ? !itemName.equals(item.itemName) : item.itemName != null) return false;
        if (length != null ? !length.equals(item.length) : item.length != null) return false;
        if (manufacturer != null ? !manufacturer.equals(item.manufacturer) : item.manufacturer != null) return false;
        if (price != null ? !price.equals(item.price) : item.price != null) return false;
        if (weight != null ? !weight.equals(item.weight) : item.weight != null) return false;
        if (width != null ? !width.equals(item.width) : item.width != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idItem;
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (width != null ? width.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "idItem=" + idItem +
                ", itemName='" + itemName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", weight=" + weight +
                ", color=" + color +
                '}';
    }

    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ItemOrder> getItemOrders() {
        return itemOrders;
    }

    public void setItemOrders(List<ItemOrder> itemOrders) {
        this.itemOrders = itemOrders;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
