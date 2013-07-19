package dto.item;

import dao.ItemDAO;
import dto.Admin;
import dto.category.CategoryDTO;
import dto.service.NavigationShop;
import entities.Category;
import entities.Item;
import entities.dictionaries.Color;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import static dto.service.NavigationAdmin.EDIT_ITEM_PAGE;
import static dto.service.NavigationAdmin.ITEMS_PAGE;
import static dto.service.Util.createMessage;
import static dto.service.Util.navigateTo;

/**
 * User: Dmitry
 * Date: 7/15/13
 * Time: 11:12 PM
 */
@ManagedBean(name = "itemDTO")
@SessionScoped
public class ItemDTO implements Serializable {

    @EJB
    private ItemDAO itemDAO;
    @ManagedProperty(value = "#{categoryDTO}")
    private CategoryDTO categoryDTO;
    @ManagedProperty(value = "#{admin}")
    private Admin admin;
    private Item selectedItem;
    private boolean allItemsShown;
    private ItemModel itemModel;

    private String newItemName;
    private String newManufacturer;
    private String newDescription;
    private int newQuantity;
    private double newPrice;
    private int newLength;
    private int newWidth;
    private int newHeight;
    private int newWeight;
    private Color newColor;
    private Category newCategory;

    @PostConstruct
    public void initialize() {
        allItemsShown = true;
        itemModel = new ItemModel(itemDAO.findAll());
    }

    @RolesAllowed("ADMIN")
    public void editItem() throws IOException {
        if (selectedItem != null) {
            navigateTo(EDIT_ITEM_PAGE);
        } else {
            createMessage("Choose item first");
        }
    }

    @RolesAllowed("ADMIN")
    public void createItem() throws IOException {
        Item item = new Item();
        item.setItemName(newItemName);
        item.setManufacturer(newManufacturer);
        item.setDescription(newDescription);
        item.setQuantity(newQuantity);
        item.setPrice(newPrice);
        item.setLength(newLength);
        item.setWidth(newWidth);
        item.setHeight(newHeight);
        item.setWeight(newWeight);
        item.setColor(newColor);
        item.setCategory(newCategory);
        itemDAO.create(item);
        navigateTo(ITEMS_PAGE);
    }

    @RolesAllowed("ADMIN")
    public void updateItem() throws IOException {
        //todo: finish updating item: add characteristics management
        navigateTo(ITEMS_PAGE);
        throw new UnsupportedOperationException("Not finished yet");
    }

    public SelectItem[] getColors() {
        SelectItem[] items = new SelectItem[Color.values().length];
        int i = 0;
        for (Color g : Color.values()) {
            items[i++] = new SelectItem(g, g.getText());
        }
        return items;
    }

    public List<Item> getItems() {
        return itemDAO.findAll();
    }

    public ItemModel getItemModel() {
        return itemModel;
    }

    public void viewItemsOfCategory() throws IOException {
        if (categoryDTO.getSelectedCategory() != null) {
            itemModel = new ItemModel(itemDAO.findPhotosOfItem(categoryDTO.getSelectedCategory()));
            allItemsShown = false;
            admin.setPageIndex(Admin.ITEMS_INDEX);
            navigateTo(ITEMS_PAGE);
        } else {
            createMessage("Choose category to view items");
        }
    }

    public void viewCatalogue() throws IOException {
        itemModel = new ItemModel((itemDAO.findPhotosOfItem(categoryDTO.getSelectedCategory())));
        navigateTo(NavigationShop.CATALOGUE_PAGE);
    }

    public void showAll() throws IOException {
        itemModel = new ItemModel(itemDAO.findAll());
        allItemsShown = true;
        navigateTo(ITEMS_PAGE);
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }

    public boolean isAllItemsShown() {
        return allItemsShown;
    }

    public String getNewItemName() {
        return newItemName;
    }

    @RolesAllowed("ADMIN")
    public void setNewItemName(String newItemName) {
        this.newItemName = newItemName;
    }

    public String getNewManufacturer() {
        return newManufacturer;
    }

    @RolesAllowed("ADMIN")
    public void setNewManufacturer(String newManufacturer) {
        this.newManufacturer = newManufacturer;
    }

    public String getNewDescription() {
        return newDescription;
    }

    @RolesAllowed("ADMIN")
    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }

    public int getNewQuantity() {
        return newQuantity;
    }

    @RolesAllowed("ADMIN")
    public void setNewQuantity(int newQuantity) {
        this.newQuantity = newQuantity;
    }

    public double getNewPrice() {
        return newPrice;
    }

    @RolesAllowed("ADMIN")
    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public int getNewLength() {
        return newLength;
    }

    @RolesAllowed("ADMIN")
    public void setNewLength(int newLength) {
        this.newLength = newLength;
    }

    public int getNewWidth() {
        return newWidth;
    }

    @RolesAllowed("ADMIN")
    public void setNewWidth(int newWidth) {
        this.newWidth = newWidth;
    }

    public int getNewHeight() {
        return newHeight;
    }

    @RolesAllowed("ADMIN")
    public void setNewHeight(int newHeight) {
        this.newHeight = newHeight;
    }

    public int getNewWeight() {
        return newWeight;
    }

    @RolesAllowed("ADMIN")
    public void setNewWeight(int newWeight) {
        this.newWeight = newWeight;
    }

    public Color getNewColor() {
        return newColor;
    }

    @RolesAllowed("ADMIN")
    public void setNewColor(Color newColor) {
        this.newColor = newColor;
    }

    public Category getNewCategory() {
        return newCategory;
    }

    @RolesAllowed("ADMIN")
    public void setNewCategory(Category newCategory) {
        this.newCategory = newCategory;
    }
}
