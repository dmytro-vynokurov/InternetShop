package dto.service;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * User: Dmytro_Vynokurov
 * Date: 7/9/13
 * Time: 4:22 PM
 */
@ManagedBean(name = "admin")
@SessionScoped
public class Admin implements Serializable {
    public static final long ADMIN_HOME_INDEX = 0;
    public static final long CATEGORIES_INDEX = 1;
    public static final long ITEMS_INDEX = 2;
    public static final long ORDERS_INDEX = 3;

    private long currentPage = 0;

    public String setPageIndex(long value) {
        currentPage = value;
        switch ((int) value) {
            case 0:
                return NavigationAdmin.ADMIN_HOME_PAGE;
            case 1:
                return NavigationAdmin.CATEGORIES_PAGE;
            case 2:
                return NavigationAdmin.ITEMS_PAGE;
            case 3:
                return NavigationAdmin.ORDERS_PAGE;
            default:
                return NavigationAdmin.ADMIN_HOME_PAGE;
        }
    }

    public long getPageIndex() {
        return currentPage;
    }

}
