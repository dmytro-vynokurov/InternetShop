package dto.catalogue;

import dao.CategoryDAO;
import dao.ItemDAO;
import dao.PhotoDAO;
import dto.cart.CartDTO;
import dto.category.CategoryModel;
import dto.item.ItemModel;
import entities.Category;
import entities.Item;
import entities.Photo;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import static dto.service.NavigationShop.*;
import static dto.service.Util.navigateTo;

/**
 * User: Dmitry
 * Date: 7/19/13
 * Time: 9:27 AM
 */
@ManagedBean(name = "catalogueDTO")
@SessionScoped
public class CatalogueDTO implements Serializable {

    @EJB
    CategoryDAO categoryDAO;
    @EJB
    ItemDAO itemDAO;
    @EJB
    PhotoDAO photoDAO;
    @ManagedProperty(value = "#{cartDTO}")
    CartDTO cartDTO;

    Category selectedCategory;
    Item selectedItem;


    public void viewItem() throws IOException {
        navigateTo(ITEM_PAGE);
    }

    public void viewItems() throws IOException {
        navigateTo(SHOP_PREFIX + CATALOGUE_PAGE);
    }

    public Object getPhoto(Item item) {
        List<Photo> photos = photoDAO.findItemsOfCategory(item);
        if (photos.isEmpty()) return "<img src=\"http://localhost:8089/web/noitem.jpg\" height=\"42\" width=\"42\">";
        else return photos.get(0);
    }

    public CategoryModel getCategoryModel() {
        return new CategoryModel(categoryDAO.findAll());
    }

    public ItemModel getItemModel() {
        return new ItemModel(itemDAO.findPhotosOfItem(selectedCategory));
    }

    public void setCartDTO(CartDTO cartDTO) {
        this.cartDTO = cartDTO;
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Category selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }
}
