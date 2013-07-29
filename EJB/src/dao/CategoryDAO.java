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
    private static final String FIND_NOT_DELETED = "findNotDeleted";

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

    public List<Category> findCategoriesWithItems(){
        return em.createQuery("SELECT c FROM Category c WHERE EXISTS (" +
                                        "SELECT i FROM Item i WHERE i.category=c)"
                                ,Category.class)
                .getResultList();
    }

    @Override
    public List<Category> findAll() {
        return em.createNamedQuery(FIND_NOT_DELETED, Category.class)
                .getResultList();
    }

    @Override
    public void remove(Category category) {
        category.setDeleted(true);
        em.merge(category);
    }

    @Override
    protected Class<Category> entityClass() {
        return Category.class;
    }
}
