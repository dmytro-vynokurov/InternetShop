package dao;

import entities.Characteristic;
import entities.Item;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/25/13
 * Time: 4:08 PM
 */
@Stateless
public class CharacteristicDAO extends GenericDAO<Characteristic> {

    public List<Characteristic> findCharacteristicsOfItem(final Item item) {
        return executeQuery(new QueryBuilder() {
            @Override
            public TypedQuery<Characteristic> buildQuery() {
                TypedQuery<Characteristic> query = em.createQuery(
                        "SELECT ct FROM Characteristic ct WHERE ct.item=:item", Characteristic.class);
                return query.setParameter("item", item);
            }
        });
    }

    @Override
    protected Class<Characteristic> entityClass() {
        return Characteristic.class;
    }
}
