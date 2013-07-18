package dto.admin.category;

import dao.CategoryDAO;
import entities.Category;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * User: Dmitry
 * Date: 7/14/13
 * Time: 2:06 PM
 */
@FacesConverter(value = "CategoryConverter", forClass = entities.Category.class)
public class CategoryConverter implements Converter {

    public static final String SPLITTER = "\u2622";

    CategoryDAO categoryDAO;

    public CategoryConverter() throws NamingException {
        categoryDAO = (CategoryDAO) new InitialContext().lookup("java:global/web/CategoryDAO");
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if ((s == null) || (s.equals(""))) return new Category();

        String[] tokens = s.split(SPLITTER);

        int idCategory = Integer.valueOf(tokens[0]);
        String categoryName = tokens[1];

        Category parentCategory;
        if (tokens.length == 3) {
            int parentCategoryId = Integer.valueOf(tokens[2]);
            parentCategory = categoryDAO.find(parentCategoryId);
        } else {
            parentCategory = null;
        }

        Category category = categoryDAO.find(idCategory);
        if (category == null) category = new Category();

        category.setCategoryName(categoryName);
        category.setParentCategory(parentCategory);

        return category;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) return "";

        Category category = (Category) o;
        StringBuilder sb = new StringBuilder();
        sb.append(category.getIdCategory());
        sb.append(SPLITTER);
        sb.append(category.getCategoryName());
        sb.append(SPLITTER);
        if (category.getParentCategory() != null)
            sb.append(category.getParentCategory().getIdCategory());
        return sb.toString();
    }

}
