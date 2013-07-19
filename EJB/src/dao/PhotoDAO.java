package dao;

import entities.Item;
import entities.Photo;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * User: Dmitry
 * Date: 7/19/13
 * Time: 2:23 PM
 */
@Stateless
public class PhotoDAO extends GenericDAO<Photo> {


    public List<Photo> findItemsOfCategory(final Item item) {
        return executeQuery(new QueryBuilder() {
            @Override
            public TypedQuery<Photo> buildQuery() {
                TypedQuery<Photo> query = em.createQuery("SELECT p FROM Photo p WHERE p.item=:item", Photo.class);
                return query.setParameter("item", item);
            }
        });
    }

    @Override
    protected Class<Photo> entityClass() {
        return Photo.class;
    }
}
