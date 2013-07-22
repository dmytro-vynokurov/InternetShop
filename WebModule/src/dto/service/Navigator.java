package dto.service;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

/**
 * User: Dmitry
 * Date: 7/19/13
 * Time: 5:56 PM
 */
@ManagedBean(name = "navigator")
@RequestScoped
public class Navigator implements Serializable {
    private static final String INDEX_ABSOLUTE_PATH = "/web/index.xhtml";
    private static final String CART_ABSOLUTE_PATH = "/web/shop/cart.xhtml";

    public static void moveToIndex() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(INDEX_ABSOLUTE_PATH);
    }

    public static void moveToCart() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(CART_ABSOLUTE_PATH);
    }
}
