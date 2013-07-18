package dto.admin.category;

import entities.Category;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

public class CategoryModel extends ListDataModel<Category> implements SelectableDataModel<Category> {

    public CategoryModel() {
    }

    public CategoryModel(List<Category> data) {
        super(data);
    }

    @Override
    public Category getRowData(String rowKey) {
        List<Category> categories = (List<Category>) getWrappedData();
        for (Category category : categories) {
            if (category.getIdCategory() == Integer.valueOf(rowKey))
                return category;
        }
        return null;
    }

    @Override
    public Object getRowKey(Category category) {
        return category.getIdCategory();
    }
}