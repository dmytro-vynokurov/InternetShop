package example;

import dao.CategoryDAO;
import entities.Category;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * User: Dmitry
 * Date: 7/18/13
 * Time: 4:50 PM
 */
@Stateless
@Path("/categoryREST")
public class CategoryREST {
    private static final String CATEGORY_DAO_LOOKUP_PATH = "java:global/rest/CategoryDAO";

    @EJB
    CategoryDAO categoryDAO;

    @GET
    @Produces("text/html")
    public String getClichedMessage() throws NamingException {
        categoryDAO = (CategoryDAO) new InitialContext().lookup(CATEGORY_DAO_LOOKUP_PATH);
        List<Category> categories = categoryDAO.findAll();
        StringBuilder result = new StringBuilder();
        boolean isFirst = true;

        result.append("{\"categories\":[");
        for (Category category : categories) {
            if (!isFirst) result.append(",");
            isFirst = false;
            result.append("{\"id\":\"").append(category.getIdCategory()).append("\",");
            result.append("\"name\":\"").append(category.getCategoryName());
            result.append("\"}");
        }
        result.append("]}");

        return result.toString();
    }

    @GET
    @Produces("application/json")
    @Path("/objects")
    public List<Category> getCategoriesAsObjects() throws NamingException {
        categoryDAO = (CategoryDAO) new InitialContext().lookup(CATEGORY_DAO_LOOKUP_PATH);
        List<Category> categories = categoryDAO.findAll();
        return categories;
    }

    @GET
    @Path("/one")
    @Produces("application/json")
    public Category getOneCategory() throws NamingException {
        categoryDAO = (CategoryDAO) new InitialContext().lookup(CATEGORY_DAO_LOOKUP_PATH);
        List<Category> categories = categoryDAO.findAll();
        return categories.get(0);
    }

    @GET
    @Produces("text/html")
    @Path("/string")
    public String getStringCategory() throws NamingException {
        categoryDAO = (CategoryDAO) new InitialContext().lookup(CATEGORY_DAO_LOOKUP_PATH);
        List<Category> categories = categoryDAO.findAll();
        return categories.get(0).toString();
    }

}