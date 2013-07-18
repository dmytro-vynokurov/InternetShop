package ejb;

import entities.Category;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * User: Dmitry
 * Date: 7/18/13
 * Time: 2:26 PM
 */
@Remote
@WebService
public interface ServiceInterface {
    @WebMethod
    List<Category> getCategories();
}
