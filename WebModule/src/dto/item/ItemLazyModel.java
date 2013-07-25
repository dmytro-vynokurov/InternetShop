package dto.item;

import dao.ItemDAO;
import entities.util.PriceRange;
import entities.Category;
import entities.Item;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;
import java.util.Map;

/**
 * User: Dmitry
 * Date: 7/22/13
 * Time: 2:28 AM
 */
public class ItemLazyModel extends LazyDataModel<Item> {
    private static final String ITEM_DAO_CONTEXT_PATH = "java:global/web/ItemDAO";

    @EJB
    ItemDAO itemDAO;
    Category category;
    List<PriceRange> priceRanges;

    public ItemLazyModel() throws NamingException {
        itemDAO = (ItemDAO) new InitialContext().lookup(ITEM_DAO_CONTEXT_PATH);
        category = null;
    }

    public ItemLazyModel(Category category) throws NamingException {
        itemDAO = (ItemDAO) new InitialContext().lookup(ITEM_DAO_CONTEXT_PATH);
        this.category = category;
    }

    public ItemLazyModel(Category category, List<PriceRange> priceRanges) throws NamingException {
        itemDAO = (ItemDAO) new InitialContext().lookup(ITEM_DAO_CONTEXT_PATH);
        this.category = category;
        this.priceRanges = priceRanges;
    }

    @Override
    public Item getRowData(String rowKey) {
        System.out.println("Row key = " + rowKey);
        int id = Integer.valueOf(rowKey);
        System.out.println("Int value = " + id);
        return itemDAO.find(id);
    }

    @Override
    public Object getRowKey(Item item) {
        return item.getIdItem();
    }

    @Override
    public List<Item> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        if (category == null) return loadAll(first, pageSize);
        else return loadByCategory(first, pageSize);
    }

    private List<Item> loadAll(int first, int pageSize) {
        List<Item> result = itemDAO.findItemsInRange(first, first + pageSize);
        if (getRowCount() <= 0) setRowCount(itemDAO.count().intValue());
        return result;
    }

    private List<Item> loadByCategory(int first, int pageSize) {
        if (PriceRange.priceFilteringEnabled(priceRanges)) {
            return loadByCategoryWithFilters(first, pageSize);
        } else {
            List<Item> result = itemDAO.findItemsOfCategoryInRange(category, first, first + pageSize);
            setRowCount(itemDAO.countItemsOfCategory(category).intValue());
            return result;
        }
    }

    private List<Item> loadByCategoryWithFilters(int first, int pageSize){
        List<Item> result = itemDAO.findItemsOfCategoryInRangeWithPriceFilters(category, priceRanges, first, first + pageSize);
        setRowCount(itemDAO.countItemsOfCategoryWithFilters(category, priceRanges).intValue());
        return result;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
