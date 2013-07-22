package dto.manager;

import dao.OrderDAO;
import entities.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.List;
import java.util.Map;

/**
 * User: Dmitry
 * Date: 7/22/13
 * Time: 2:28 AM
 */
public class OrderPickerModel extends LazyDataModel<Order> {

    OrderDAO orderDAO;

    public OrderPickerModel(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public Order getRowData(String rowKey) {
        System.out.println("Row key = "+rowKey);
        int id = Integer.valueOf(rowKey);
        System.out.println("Int value = "+id);
        return orderDAO.find(id);
    }

    @Override
    public Object getRowKey(Order order) {
        return order.getIdOrder();
    }

    @Override
    public List<Order> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        List<Order> result = orderDAO.findSortedBySumWaitingForProcessingInRange(first, first + pageSize, true);
        System.out.println("In lazy model: "+result);
        return result;
    }


}
