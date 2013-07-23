package dao;

import entities.Category;
import entities.Item;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/25/13
 * Time: 4:10 PM
 */
@Stateless
public class ItemDAO extends GenericDAO<Item> {

    public List<Item> findItemsOfCategory(final Category category) {
        return executeQuery(new QueryBuilder() {
            @Override
            public TypedQuery<Item> buildQuery() {
                TypedQuery<Item> query = em.createQuery("SELECT i FROM Item i WHERE i.category=:category", Item.class);
                return query.setParameter("category", category);
            }
        });
    }

    public List<Item> findItemsInRange(final int fromId, final int toId) {
        return executeQuery(new QueryBuilder() {
            @Override
            public TypedQuery<Item> buildQuery() {
                TypedQuery<Item> query = em.createQuery("SELECT i FROM Item i WHERE i.idItem<:toId AND i.idItem>:fromId", Item.class);
                return query.setParameter("fromId", fromId).setParameter("toId", toId);
            }
        });
    }

    @Override
    protected Class<Item> entityClass() {
        return Item.class;
    }
}
