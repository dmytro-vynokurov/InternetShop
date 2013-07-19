package dto.cart;

import entities.Category;
import entities.ItemOrder;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 * User: Dmitry
 * Date: 7/19/13
 * Time: 8:55 AM
 */
public class CartModel extends ListDataModel<ItemOrder> implements SelectableDataModel<ItemOrder> {

    public CartModel() {
    }

    public CartModel(List<ItemOrder> data) {
        super(data);
    }
    @Override
    public Object getRowKey(ItemOrder entry) {
        return makeKey(entry);
    }

    @Override
    public ItemOrder getRowData(String rowKey) {
        List<ItemOrder> entries = (List<ItemOrder>) getWrappedData();
        for (ItemOrder entry : entries) {
            if (makeKey(entry).equals(rowKey))
                return entry;
        }
        return null;
    }

    private String makeKey(ItemOrder entry){
               return ""+entry.getItem().getIdItem()+
                       "/"+entry.getOrder().getIdOrder();
    }
}
