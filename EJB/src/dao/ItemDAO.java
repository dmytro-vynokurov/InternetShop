package dao;

import entities.Category;
import entities.Item;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/25/13
 * Time: 4:10 PM
 */
@Stateless
public class ItemDAO extends GenericDAO<Item> {
    private static final String COUNT_ITEMS_OF_CATEGORY ="SELECT COUNT(*) FROM Item i WHERE i.category=:category";


    public List<Item> findItemsOfCategory(final Category category) {
        return executeQuery(new QueryBuilder() {
            @Override
            public TypedQuery<Item> buildQuery() {
                return em.createQuery("SELECT i FROM Item i WHERE i.category=:category", Item.class)
                        .setParameter("category", category);
            }
        });
    }

    public List<Item> findItemsInRange(final int fromId, final int toId) {
        return executeQuery(new QueryBuilder() {
            @Override
            public TypedQuery<Item> buildQuery() {
                return em.createQuery("SELECT i FROM Item i WHERE i.idItem<:toId AND i.idItem>:fromId", Item.class)
                        .setParameter("fromId", fromId).setParameter("toId", toId);
            }
        });
    }

    public List<Item> findItemsOfCategoryInRange(final Category category, final int fromId,final int toId){
        return executeQuery(new QueryBuilder() {
            @Override
            public TypedQuery<Item> buildQuery() {
                return em.createQuery(
                        "SELECT i FROM Item i WHERE i.category=:category AND i.idItem<:toId AND i.idItem>:fromId", Item.class)
                        .setParameter("category", category)
                        .setParameter("fromId", fromId).setParameter("toId", toId);
            }
        });
    }

    public int countItemsOfCategory(final Category category){
         Query query = em.createQuery(COUNT_ITEMS_OF_CATEGORY, Item.class)
                        .setParameter("category", category);
         return (int) query.getSingleResult();
    }

    @Override
    protected Class<Item> entityClass() {
        return Item.class;
    }
}
