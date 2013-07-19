package dto.catalogue;

import dao.CategoryDAO;
import dao.ItemDAO;
import dto.cart.CartDTO;
import entities.Category;
import entities.Item;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 * User: Dmitry
 * Date: 7/19/13
 * Time: 9:27 AM
 */
@ManagedBean(name="catalogueDTO")
@SessionScoped
public class CatalogueDTO implements Serializable{

    @EJB
    CategoryDAO categoryDAO;
    @EJB
    ItemDAO itemDAO;
    @ManagedProperty(value = "#{cartDTO}")
    CartDTO cartDTO;

    Category selectedCategory;
    Item selectedItem;



    public List<Category> getCategories(){
        return categoryDAO.findAll();
    }

    public List<Item> getItems(){
        return itemDAO.findItemsOfCategory(selectedCategory);
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
