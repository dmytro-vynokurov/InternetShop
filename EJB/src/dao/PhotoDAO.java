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
    private final static String DEFAULT_ITEM_PHOTO = "F:\\projects\\try2\\InternetShop\\WebModule\\web\\nophoto.jpg";


    public List<Photo> findPhotosOfItem(final Item item) {
        return executeQuery(new QueryBuilder() {
            @Override
            public TypedQuery<Photo> buildQuery() {
                TypedQuery<Photo> query = em.createQuery("SELECT p FROM Photo p WHERE p.item=:item", Photo.class);
                return query.setParameter("item", item);
            }
        });
    }

    //todo: write Photo findOnePhotoOfItem(Item item)

    @Override
    protected Class<Photo> entityClass() {
        return Photo.class;
    }
}
