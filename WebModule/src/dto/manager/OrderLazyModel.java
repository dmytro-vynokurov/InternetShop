package dto.manager;

import dao.OrderDAO;
import entities.Order;
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
public class OrderLazyModel extends LazyDataModel<Order> {
    private static final String ORDER_DAO_CONTEXT_PATH = "java:global/web/OrderDAO";

    @EJB
    OrderDAO orderDAO;

    public OrderLazyModel() throws NamingException {
        orderDAO = (OrderDAO) new InitialContext().lookup(ORDER_DAO_CONTEXT_PATH);
    }

    @Override
    public Order getRowData(String rowKey) {
        System.out.println("Row key = " + rowKey);
        int id = Integer.valueOf(rowKey);
        System.out.println("Int value = " + id);
        return orderDAO.find(id);
    }

    @Override
    public Object getRowKey(Order order) {
        return order.getIdOrder();
    }

    @Override
    public List<Order> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        List<Order> result = orderDAO.findSortedBySumWaitingForProcessingInRange(first, first + pageSize, true);
        System.out.println("In lazy model: " + result);
        return result;
    }


}
