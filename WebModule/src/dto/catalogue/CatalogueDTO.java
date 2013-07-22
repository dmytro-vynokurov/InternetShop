package dto.catalogue;

import dao.*;
import dto.cart.CartDTO;
import dto.category.CategoryModel;
import dto.item.ItemModel;
import entities.Category;
import entities.Characteristic;
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
    @EJB
    private VideoDAO videoDAO;
    @EJB
    private CharacteristicDAO characteristicDAO;
    @ManagedProperty(value = "#{cartDTO}")
    CartDTO cartDTO;

    Category selectedCategory;
    Item selectedItem;

    public Object getPhoto(Item item) {
        //todo: make loading images from database
        List<Photo> photos = photoDAO.findPhotosOfItem(item);
        if (photos.isEmpty()) return "<img src=\"http://localhost:8089/web/nophoto.jpg\" height=\"42\" width=\"42\">";
        else return photos.get(0);
    }

    public String getVideo() {
        System.out.println(videoDAO.findVideoOfItem(selectedItem).getUrl());
        return videoDAO.findVideoOfItem(selectedItem).getUrl();
    }

    public void viewItem() throws IOException {
        navigateTo(ITEM_PAGE);
    }

    public void viewItems() throws IOException {
        navigateTo(SHOP_PREFIX + CATALOGUE_PAGE);
    }

    public CategoryModel getCategoryModel() {
        return new CategoryModel(categoryDAO.findAll());
    }

    public List<Characteristic> getCharacteristics() {
        return characteristicDAO.findCharacteristicsOfItem(selectedItem);
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

    public void setSelectedItem(Item selectedItem) throws IOException {
        this.selectedItem = selectedItem;
        navigateTo(ITEM_PAGE);

    }
}
