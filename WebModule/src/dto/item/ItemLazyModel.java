package dto.item;

import dao.ItemDAO;
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

    public ItemLazyModel() throws NamingException {
        itemDAO = (ItemDAO) new InitialContext().lookup(ITEM_DAO_CONTEXT_PATH);
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
        List<Item> result = itemDAO.findItemsInRange(first, first + pageSize);
        System.out.println("In lazy model: " + result);
        if (getRowCount() <= 0) setRowCount(itemDAO.count().intValue());
        return result;
    }


}
