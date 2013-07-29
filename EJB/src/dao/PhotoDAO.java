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
    private static final String FIND_PHOTOS_OF_ITEM = "findPhotosOfItem";

    public List<Photo> findPhotosOfItem(final Item item) {
        return em.createNamedQuery(FIND_PHOTOS_OF_ITEM, Photo.class)
                .setParameter("item", item)
                .getResultList();
    }

    @Override
    protected Class<Photo> entityClass() {
        return Photo.class;
    }
}
