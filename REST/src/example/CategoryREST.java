package example;

import dao.CategoryDAO;
import entities.Category;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * User: Dmitry
 * Date: 7/18/13
 * Time: 4:50 PM
 */
@Stateless
@Path("/categoryREST")
public class CategoryREST {

    @EJB
    CategoryDAO categoryDAO;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getClichedMessage() {
        List<Category> categories = categoryDAO.findAll();
        StringBuilder result = new StringBuilder();

        result.append("{categories:[");
        for (Category category : categories) {
            result.append("{id:").append(category.getIdCategory()).append(",");
            result.append("name:").append(category.getCategoryName()).append(",");
            result.append("parentId:").append(category.getParentCategory().getIdCategory());
            result.append("}");
        }
        result.append("]}");

        return result.toString();
    }

}