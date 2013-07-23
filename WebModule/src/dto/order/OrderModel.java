package dto.order;

import entities.Order;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 * User: Dmitry
 * Date: 7/19/13
 * Time: 2:26 AM
 */
public class OrderModel extends ListDataModel<Order> implements SelectableDataModel<Order> {

    public OrderModel() {
    }

    public OrderModel(List<Order> data) {
        super(data);
    }

    @Override
    public Order getRowData(String rowKey) {
        List<Order> orders = (List<Order>) getWrappedData();
        for (Order order : orders) {
            if (order.getIdOrder() == Integer.valueOf(rowKey))
                return order;
        }
        return null;
    }

    @Override
    public Object getRowKey(Order category) {
        return category.getIdOrder();
    }
}
