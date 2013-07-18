package dto.admin.characteristictype;

import dao.CharacteristicTypeDAO;
import dto.admin.category.CategoryDTO;
import entities.CharacteristicType;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.IOException;
import java.io.Serializable;

import static dto.admin.Navigation.*;


/**
 * User: Dmitry
 * Date: 7/16/13
 * Time: 4:58 PM
 */
@ManagedBean(name = "characteristicTypeDTO")
@ViewScoped
public class CharacteristicTypeDTO implements Serializable {

    @EJB
    private CharacteristicTypeDAO characteristicTypeDAO;
    @ManagedProperty(value = "#{categoryDTO}")
    private CategoryDTO categoryDTO;

    private CharacteristicTypeModel characteristicTypeModel;
    private CharacteristicType selectedCharacteristicType;

    @PostConstruct
    @RolesAllowed("ADMIN")
    public void initializeDataModel() {
        characteristicTypeModel = new CharacteristicTypeModel(
                characteristicTypeDAO.findCharacteristicTypesOfCategory(categoryDTO.getSelectedCategory()));
    }

    @RolesAllowed("ADMIN")
    public void addCharacteristicType() throws IOException {
        CharacteristicType characteristicType = new CharacteristicType();
        characteristicType.setCategory(categoryDTO.getSelectedCategory());
        characteristicType.setCharacteristicTypeName("New characteristic type");
        characteristicTypeDAO.create(characteristicType);
        initializeDataModel();
        navigateTo(EDIT_CATEGORY_PAGE);
    }

    @RolesAllowed("ADMIN")
    public void deleteCharacteristicType() {
        if (selectedCharacteristicType != null) {
            RequestContext.getCurrentInstance().execute("confirm_delete_CT.show();");
        } else {
            createMessage("Choose characteristic type first");
        }
    }

    @RolesAllowed("ADMIN")
    public void removeCharacteristicType() throws IOException {
        characteristicTypeDAO.remove(selectedCharacteristicType);
        initializeDataModel();
        navigateTo(EDIT_CATEGORY_PAGE);
    }

    @RolesAllowed("ADMIN")
    public void updateCharacteristicType(CellEditEvent event) throws IOException {
        System.out.println("Selected:\t" + selectedCharacteristicType);
        characteristicTypeDAO.update(selectedCharacteristicType);
        initializeDataModel();
        navigateTo(EDIT_CATEGORY_PAGE);
    }

    public CharacteristicTypeModel getCharacteristicTypeModel() {
        return characteristicTypeModel;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    public CharacteristicType getSelectedCharacteristicType() {
        return selectedCharacteristicType;
    }

    @RolesAllowed("ADMIN")
    public void setSelectedCharacteristicType(CharacteristicType selectedCharacteristicType) {
        this.selectedCharacteristicType = selectedCharacteristicType;
    }
}