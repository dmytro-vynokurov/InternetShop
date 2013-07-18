package dto.admin.characteristic;

import dao.CharacteristicDAO;
import dao.CharacteristicTypeDAO;
import dto.admin.item.ItemDTO;
import entities.Characteristic;
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
import java.util.List;

import static dto.admin.Navigation.*;

/**
 * User: Dmitry
 * Date: 7/17/13
 * Time: 2:34 PM
 */
@ManagedBean
@ViewScoped
public class CharacteristicDTO implements Serializable {
    @EJB
    private CharacteristicDAO characteristicDAO;
    @EJB
    private CharacteristicTypeDAO characteristicTypeDAO;
    @ManagedProperty(value = "#{itemDTO}")
    private ItemDTO itemDTO;


    private CharacteristicModel characteristicModel;
    private Characteristic selectedCharacteristic;

    @PostConstruct
    public void initializeDataModel() {
        List<Characteristic> characteristics;

        List<CharacteristicType> characteristicTypes = getPossibleCharacteristicTypes();
        characteristics = addMissingCharacteristics(characteristicTypes, getExistingCharacteristics());

        characteristicModel = new CharacteristicModel(characteristics);
    }

    private List<Characteristic> getExistingCharacteristics() {
        return characteristicDAO.findCharacteristicsOfItem(itemDTO.getSelectedItem());
    }

    private List<CharacteristicType> getPossibleCharacteristicTypes() {
        return characteristicTypeDAO.findCharacteristicTypesOfCategory(itemDTO.getSelectedItem().getCategory());
    }

    private List<Characteristic> addMissingCharacteristics
            (List<CharacteristicType> characteristicTypes, List<Characteristic> characteristics) {
        Characteristic characteristic;
        for (CharacteristicType characteristicType : characteristicTypes) {
            if (!existsCharacteristicOfType(characteristics, characteristicType)) {
                characteristic = new Characteristic();
                characteristic.setCharacteristicType(characteristicType);
                characteristic.setItem(itemDTO.getSelectedItem());
                characteristics.add(characteristic);
            }
        }
        return characteristics;
    }

    private boolean existsCharacteristicOfType(List<Characteristic> characteristics, CharacteristicType characteristicType) {
        CharacteristicType tempType;
        for (Characteristic characteristic : characteristics) {
            if (characteristicType.getIdCharacteristicType() ==
                    characteristic.getCharacteristicType().getIdCharacteristicType()) return true;
        }
        return false;
    }


    @RolesAllowed("ADMIN")
    public void addCharacteristic() throws IOException {
        Characteristic characteristic = new Characteristic();
        characteristic.setItem(itemDTO.getSelectedItem());
//        characteristic.setCharacteristicName("New characteristic ");
        characteristicDAO.create(characteristic);
        initializeDataModel();
        navigateTo(EDIT_CATEGORY_PAGE);
    }

    @RolesAllowed("ADMIN")
    public void deleteCharacteristic() {
        if (selectedCharacteristic != null) {
            RequestContext.getCurrentInstance().execute("confirm_delete_CT.show();");
        } else {
            createMessage("Choose characteristic first");
        }
    }

    @RolesAllowed("ADMIN")
    public void removeCharacteristic() throws IOException {
        characteristicDAO.remove(selectedCharacteristic);
        initializeDataModel();
        navigateTo(EDIT_CATEGORY_PAGE);
    }

    @RolesAllowed("ADMIN")
    public void updateCharacteristic(CellEditEvent event) throws IOException {
        System.out.println("Selected:\t" + selectedCharacteristic);
        characteristicDAO.update(selectedCharacteristic);
        initializeDataModel();
        navigateTo(EDIT_CATEGORY_PAGE);
    }

    public void setItemDTO(ItemDTO itemDTO) {
        this.itemDTO = itemDTO;
    }

    public CharacteristicModel getCharacteristicModel() {
        return characteristicModel;
    }

    public Characteristic getSelectedCharacteristic() {
        return selectedCharacteristic;
    }

    public void setSelectedCharacteristic(Characteristic selectedCharacteristic) {
        this.selectedCharacteristic = selectedCharacteristic;
    }
}
