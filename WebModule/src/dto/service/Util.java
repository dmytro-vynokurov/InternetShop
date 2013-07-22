package dto.service;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * User: Dmitry
 * Date: 7/19/13
 * Time: 2:02 AM
 */
public class Util {
    public static void navigateTo(String page) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(page + ".xhtml");
    }

    public static void createMessage(String text) {
        FacesMessage message = new FacesMessage(text);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
