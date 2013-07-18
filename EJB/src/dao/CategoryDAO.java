package dao;

import entities.Category;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/21/13
 * Time: 7:13 PM
 */
@Stateless
public class CategoryDAO extends GenericDAO<Category> {

    public Category getDefaultCategory() {
        return find(1);
    }

    public boolean isDefaultCategory(Category category) {
        return category.getIdCategory() == 1;
    }

    public boolean isAncestor(Category ancestor, Category descendant) {
        if (isDefaultCategory(ancestor)) return true;
        if (isDefaultCategory(descendant)) return false;
        if (descendant.getIdCategory() == ancestor.getIdCategory()) return true;
        else return isAncestor(ancestor, descendant.getParentCategory());
    }

    public List<Category> getAllChildCategories(Category category) {
        List<Category> result = new ArrayList<>();
        for (Category temp : findAll()) {
            if (isAncestor(category, temp)) result.add(temp);
        }
        return result;
    }

    @Override
    protected Class<Category> entityClass() {
        return Category.class;
    }
}
