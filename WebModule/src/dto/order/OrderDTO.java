package dto.order;

import dao.OrderDAO;
import entities.Order;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * User: Dmitry
 * Date: 7/19/13
 * Time: 2:22 AM
 */
@ManagedBean(name="orderDTO")
@SessionScoped
public class OrderDTO implements Serializable{

    @EJB
    OrderDAO orderDAO;
    Order selectedOrder;




}
