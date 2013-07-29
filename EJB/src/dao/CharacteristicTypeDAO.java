package dao;

import entities.Category;
import entities.CharacteristicType;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/25/13
 * Time: 4:09 PM
 */
@Stateless
public class CharacteristicTypeDAO extends GenericDAO<CharacteristicType> {
    private static final String FIND_CHARACTERISTIC_TYPES_OF_CATEGORY="findCharacteristicTypesOfCategory";

    public List<CharacteristicType> findCharacteristicTypesOfCategory(final Category category) {
        return em.createNamedQuery(FIND_CHARACTERISTIC_TYPES_OF_CATEGORY, CharacteristicType.class)
                .setParameter("category", category)
                .getResultList();
    }

    @Override
    protected Class<CharacteristicType> entityClass() {
        return CharacteristicType.class;
    }
}
