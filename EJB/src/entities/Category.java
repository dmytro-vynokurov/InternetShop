package entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/20/13
 * Time: 5:19 PM
 */
@Entity
@Table(name = "CATEGORY", catalog = "", schema = "INTERNETSHOP")
public class Category implements Serializable {
    @Column(name = "ID_CATEGORY", nullable = false, insertable = true, updatable = true, length = 6, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_Category")
    @SequenceGenerator(name = "S_Category", sequenceName = "S_CATEGORY", allocationSize = 1)
    private int idCategory;
    @Column(name = "CATEGORY_NAME", nullable = false, insertable = true, updatable = true, length = 40, precision = 0)
    @Basic
    private String categoryName;
    @ManyToOne
    @JoinColumn(name = "PARENT_CATEGORY_ID", referencedColumnName = "ID_CATEGORY")
    private Category parentCategory;
//    @OneToMany(mappedBy = "parentCategory", orphanRemoval = true)
//    private List<Category> childCategories;
//    @OneToMany(mappedBy = "category")
//    private transient List<CharacteristicType> characteristicTypes;
//    @OneToMany(mappedBy = "category")
//    private transient List<Item> items;

    public Category() {
        parentCategory = null;
//        childCategories = null;
//        characteristicTypes = null;
//        items = null;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    final public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        if (idCategory != category.idCategory) return false;
        if (categoryName != null ? !categoryName.equals(category.categoryName) : category.categoryName != null)
            return false;

        return true;
    }

    @Override
    final public int hashCode() {
        int result = idCategory;
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "idCategory=" + idCategory +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

//    public List<Category> getChildCategories() {
//        return childCategories;
//    }
//
//    public void setChildCategories(List<Category> childCategories) {
//        this.childCategories = childCategories;
//    }
////
//    public List<CharacteristicType> getCharacteristicTypes() {
//        return characteristicTypes;
//    }
//
//    public void setCharacteristicTypes(List<CharacteristicType> characteristicTypes) {
//        this.characteristicTypes = characteristicTypes;
//    }
//
//    public List<Item> getItems() {
//        return items;
//    }
//
//    public void setItems(List<Item> items) {
//        this.items = items;
//    }
}
