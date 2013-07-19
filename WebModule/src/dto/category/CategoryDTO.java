package dto.category;

import dao.CategoryDAO;
import entities.Category;
import org.primefaces.context.RequestContext;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import static dto.service.NavigationAdmin.*;
import static dto.service.Util.createMessage;
import static dto.service.Util.navigateTo;

/**
 * User: Dmytro_Vynokurov
 * Date: 7/9/13
 * Time: 6:46 PM
 */
@ManagedBean(name = "categoryDTO")
@SessionScoped
public class CategoryDTO implements Serializable {

    @EJB
    private CategoryDAO categoryDAO;
    private Category selectedCategory;
    private Category parentCategory;

    private String newCategoryName;

    @RolesAllowed("ADMIN")
    public void addCategory() throws IOException {
        if (selectedCategory != null) {
            parentCategory = selectedCategory;
            navigateTo(ADD_CATEGORY_PAGE);
        } else {
            createMessage("Choose parent category first");
        }
    }

    @RolesAllowed("ADMIN")
    public void editCategory() throws IOException {
        if (selectedCategory != null) {
            navigateTo(EDIT_CATEGORY_PAGE);
        } else {
            createMessage("Choose category to edit");
        }
    }

    @RolesAllowed("ADMIN")
    public void deleteCategory() {
        if (categoryDAO.isDefaultCategory(selectedCategory)) {
            createMessage("Cannot delete default category");
        } else if (selectedCategory != null) {
            RequestContext.getCurrentInstance().execute("confirm_delete.show();");
        } else {
            createMessage("Choose category to delete");
        }
    }

    @RolesAllowed("ADMIN")
    public void createCategory() throws IOException {
        Category category = new Category();
        category.setCategoryName(newCategoryName);
        category.setParentCategory(parentCategory);
        categoryDAO.create(category);
        navigateTo(CATEGORIES_PAGE);
    }

    @RolesAllowed("ADMIN")
    public void updateCategory() throws IOException {
        if (categoryDAO.isDefaultCategory(selectedCategory) && parentCategory != null) {
            parentCategory = null;
            createMessage("Default category cannot have parent category");
        } else if (haveSameId(selectedCategory, parentCategory)) {
            createMessage("Parent category cannot reference to itself");
        } else if (categoryDAO.isAncestor(selectedCategory, parentCategory)) {
            createMessage("Chosen parent category(" +
                    parentCategory.getCategoryName() +
                    ") is already a descendant to current category(" +
                    selectedCategory.getCategoryName() + ")");
        } else {
            selectedCategory.setParentCategory(parentCategory);
            categoryDAO.update(selectedCategory);
            navigateTo(CATEGORIES_PAGE);
        }
    }

    private boolean haveSameId(Category first, Category second) {
        return ((int) first.getIdCategory()) == ((int) second.getIdCategory());
    }

    @RolesAllowed("ADMIN")
    public void removeCategory() throws IOException {
        categoryDAO.remove(selectedCategory);
        navigateTo(CATEGORIES_PAGE);
    }

    public List<Category> getCategories() {
        return categoryDAO.findAll();
    }

    public CategoryModel getCategoryModel() {
        return new CategoryModel(getCategories());
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Category selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    @RolesAllowed("ADMIN")
    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getNewCategoryName() {
        return newCategoryName;
    }

    @RolesAllowed("ADMIN")
    public void setNewCategoryName(String newCategoryName) {
        this.newCategoryName = newCategoryName;
    }
}
