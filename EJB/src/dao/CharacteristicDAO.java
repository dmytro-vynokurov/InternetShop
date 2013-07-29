package dao;

import entities.Characteristic;
import entities.Item;

import javax.ejb.Stateless;
import java.util.List;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/25/13
 * Time: 4:08 PM
 */
@Stateless
public class CharacteristicDAO extends GenericDAO<Characteristic> {
    private static final String FIND_CHARACTERISTICS_OF_ITEM = "findCharacteristicsOfItem";

    public List<Characteristic> findCharacteristicsOfItem(final Item item) {
        return em.createNamedQuery(FIND_CHARACTERISTICS_OF_ITEM, Characteristic.class)
                .setParameter("item", item)
                .getResultList();

    }

    @Override
    protected Class<Characteristic> entityClass() {
        return Characteristic.class;
    }
}
