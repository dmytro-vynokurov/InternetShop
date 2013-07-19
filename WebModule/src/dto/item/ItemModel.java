package dto.item;

import entities.Item;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 * User: Dmitry
 * Date: 7/15/13
 * Time: 11:28 PM
 */
public class ItemModel extends ListDataModel<Item> implements SelectableDataModel<Item> {

    public ItemModel() {
    }

    public ItemModel(List<Item> data) {
        super(data);
    }

    @Override
    public Object getRowKey(Item item) {
        return item.getIdItem();
    }

    @Override
    public Item getRowData(String rowKey) {
        List<Item> items = (List<Item>) getWrappedData();
        for (Item item : items) {
            if (item.getIdItem() == Integer.valueOf(rowKey))
                return item;
        }
        return null;
    }
}
