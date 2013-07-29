package dao;

import entities.Category;
import entities.Item;
import entities.util.PriceRange;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/25/13
 * Time: 4:10 PM
 */
@Stateless
public class ItemDAO extends GenericDAO<Item> {
    private static final String COUNT_ITEMS_OF_CATEGORY = "countItemsOfCategory";
    private static final String SELECT_BY_CATEGORY = "findItemsOfCategory";
    private static final String FIND_ITEMS_IN_RANGE = "findItemsInRange";
    private static final String FIND_ITEMS_OF_CATEGORY = "findItemsOfCategory";
    private static final String FIND_MIN_PRICE = "findMinPrice";
    private static final String FIND_MAX_PRICE = "findMaxPrice";
    private static final String FIND_MIN_PRICE_OF_CATEGORY = "findMinPriceOfCategory";
    private static final String FIND_MAX_PRICE_OF_CATEGORY = "findMaxPriceOfCategory";


    public List<Item> findItemsOfCategory(final Category category) {
        return em.createNamedQuery(FIND_ITEMS_OF_CATEGORY, Item.class)
                .setParameter("category", category)
                .getResultList();
    }

    public List<Item> findItemsInRange(final int fromId, final int toId) {
        return em.createNamedQuery(FIND_ITEMS_IN_RANGE, Item.class)
                .setParameter("fromId", fromId).setParameter("toId", toId)
                .getResultList();

    }

    public List<Item> findItemsOfCategoryInRange(final Category category, final int fromIndex, final int toIndex) {
        return em.createNamedQuery(SELECT_BY_CATEGORY, Item.class)
                .setParameter("category", category)
                .setFirstResult(fromIndex)
                .setMaxResults(toIndex - fromIndex)
                .getResultList();
    }

    public List<Item> findItemsOfCategoryInRangeWithPriceFilters(Category category, List<PriceRange> priceRanges,
                                                                 int fromIndex, int toIndex) {
        System.out.println("Price ranges in DAO: " + priceRanges);
        Query query = createQueryWithPriceFilters(SELECT_BY_CATEGORY, category, priceRanges, Item.class);
        List<Item> result = (List<Item>) query
                .setFirstResult(fromIndex)
                .setMaxResults(toIndex - fromIndex)
                .getResultList();
        for (Item item : result) {
            System.out.println(item.getPrice());
        }
        return result;
    }

    public Long countItemsOfCategory(final Category category) {
        Query query = em.createNamedQuery(COUNT_ITEMS_OF_CATEGORY, Long.class)
                .setParameter("category", category);
        return (Long) query.getSingleResult();
    }

    public Long countItemsOfCategoryWithFilters(final Category category, List<PriceRange> priceRanges) {
        Query query = createQueryWithPriceFilters(COUNT_ITEMS_OF_CATEGORY, category, priceRanges, Long.class);
        return (Long) query.getSingleResult();
    }

    private Query createQueryWithPriceFilters(String queryString, Category category,
                                              List<PriceRange> priceRanges, Class<?> resultClass) {

        StringBuilder queryBuilder = prepareQueryWithFilters(queryString, priceRanges);

        System.out.println("Running query:\n" + queryBuilder.toString());

        Query query = initializeQueryWithFilters(category, priceRanges, resultClass, queryBuilder);

        return query;
    }

    private StringBuilder prepareQueryWithFilters(String queryString, List<PriceRange> priceRanges) {
        StringBuilder queryBuilder = new StringBuilder(queryString);
        if (PriceRange.priceFilteringEnabled(priceRanges)) {
            boolean noFiltersBefore = true;
            queryBuilder.append(" AND (");
            for (int i = 0; i < priceRanges.size(); i++) {
                if (priceRanges.get(i).isChecked()) {
                    if (noFiltersBefore) noFiltersBefore = false;
                    else queryBuilder.append(" OR ");

                    queryBuilder.append("(");
                    queryBuilder.append("i.price>:pl").append(i);
                    queryBuilder.append(" AND ");
                    queryBuilder.append("i.price<:pu").append(i);
                    queryBuilder.append(")");
                }
            }
            queryBuilder.append(")");
        }
        return queryBuilder;
    }

    private Query initializeQueryWithFilters(Category category, List<PriceRange> priceRanges, Class<?> resultClass, StringBuilder queryBuilder) {
        Query query = em.createNamedQuery(queryBuilder.toString(), resultClass)
                .setParameter("category", category);

        for (int i = 0; i < priceRanges.size(); i++) {
            if (priceRanges.get(i).isChecked()) {
                query.setParameter("pl" + i, priceRanges.get(i).getLower());
                query.setParameter("pu" + i, priceRanges.get(i).getUpper());
            }
        }
        return query;
    }

    public Double findMaxPrice() {
        return em.createNamedQuery(FIND_MAX_PRICE, Double.class)
                .getSingleResult();
    }

    public Double findMinPrice() {
        return em.createNamedQuery(FIND_MIN_PRICE, Double.class)
                .getSingleResult();
    }

    public Double findMaxPriceInCategory(Category category) {
        return em.createNamedQuery(FIND_MAX_PRICE_OF_CATEGORY, Double.class)
                .setParameter("category", category)
                .getSingleResult();
    }

    public Double findMinPriceInCategory(Category category) {
        return em.createNamedQuery(FIND_MIN_PRICE_OF_CATEGORY, Double.class)
                .setParameter("category", category)
                .getSingleResult();
    }

    @Override
    protected Class<Item> entityClass() {
        return Item.class;
    }
}
