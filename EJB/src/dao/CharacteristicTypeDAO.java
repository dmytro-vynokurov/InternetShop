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

    public List<CharacteristicType> findCharacteristicTypesOfCategory(final Category category) {
        return executeQuery(new QueryBuilder() {
            @Override
            public TypedQuery<CharacteristicType> buildQuery() {
                TypedQuery<CharacteristicType> query = em.createQuery("SELECT ct FROM CharacteristicType ct WHERE ct.category=:category", CharacteristicType.class);
                return query.setParameter("category", category);
            }
        });
    }

    @Override
    protected Class<CharacteristicType> entityClass() {
        return CharacteristicType.class;
    }
}
