package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/17/13
 * Time: 4:53 PM
 */
public abstract class GenericDAO<T> {

    @PersistenceContext(unitName = "ShopPersistenceUnit")
    protected EntityManager em;

    public void create(T object) {
        em.persist(object);
    }

    public T update(T element) {
        T result = null;
        result = em.merge(element);
        return result;
    }

    public void remove(T element) {
        em.remove(em.merge(element));
    }

    public List<T> findAll() {
        return executeQuery(new QueryBuilder() {
            @Override
            public Query buildQuery() {
                return em.createQuery("SELECT o FROM " + entityClass().getSimpleName() + " o", entityClass());
            }
        });
    }

    public T find(Object id) {
        T result;
        result = em.find(entityClass(), id);
        return result;
    }

    public Long count() {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(entityClass())));
        return em.createQuery(cq).getSingleResult();
    }

    protected List<T> executeQuery(QueryBuilder<T> queryBuilder) {
        List<T> result;
        Query query = queryBuilder.buildQuery();
        result = (List<T>) query.getResultList();
        return result;
    }

    protected abstract Class<T> entityClass();

    protected abstract class QueryBuilder<T> {
        public abstract Query buildQuery();
    }
}
