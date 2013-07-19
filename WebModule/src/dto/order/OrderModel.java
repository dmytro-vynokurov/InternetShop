package dto.order;

import dao.OrderDAO;
import entities.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;
import java.util.Map;

/**
 * User: Dmitry
 * Date: 7/19/13
 * Time: 2:26 AM
 */
public class OrderModel extends LazyDataModel<Order> {
    private static final String ORDER_DAO_CONTEXT_PATH="java:global/web/orderDAO";

    OrderDAO orderDAO;

    @PostConstruct
    public void initialize() throws NamingException {
        orderDAO=(OrderDAO)new InitialContext().lookup(ORDER_DAO_CONTEXT_PATH);
    }

    @Override
    public List<Order> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        if(sortField!="sum") return orderDAO.findAll();

        boolean descending=(sortOrder==SortOrder.DESCENDING);
        return orderDAO.findSortedBySumInRange(first,first+pageSize,descending);
    }
}
