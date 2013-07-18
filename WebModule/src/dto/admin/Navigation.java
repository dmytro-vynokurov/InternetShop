package dto.admin;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * User: Dmitry
 * Date: 7/14/13
 * Time: 11:41 AM
 */
public class Navigation {
    public static final String ADMIN_HOME_PAGE = "admin_home";
    public static final String CATEGORIES_PAGE = "categories";
    public static final String ORDERS_PAGE = "orders";
    public static final String ITEMS_PAGE = "items";

    public static final String ADD_CATEGORY_PAGE = "add_category";
    public static final String EDIT_CATEGORY_PAGE = "edit_category";

    public static final String ADD_ITEM_PAGE = "add_item";
    public static final String EDIT_ITEM_PAGE = "edit_item";

    public static void navigateTo(String page) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(page + ".xhtml");
    }

    public static void createMessage(String text) {
        FacesMessage message = new FacesMessage(text);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
