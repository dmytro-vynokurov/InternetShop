package ejb;

import dao.CategoryDAO;
import entities.Category;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * User: Dmitry
 * Date: 7/18/13
 * Time: 2:26 PM
 */
@Stateless
@WebService(portName = "MyWSPort",
        name = "ServiceBean",
        serviceName = "MyWSService",
        targetNamespace = "http://127.0.0.1:8089/wsdl",
        endpointInterface = "ejb.ServiceInterface")
public class ServiceBean implements ServiceInterface {

    @EJB
    CategoryDAO categoryDAO;

    @Override
    @WebMethod
    public List<Category> getCategories() {
        return categoryDAO.findAll();
    }
}
