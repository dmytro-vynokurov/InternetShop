package dto.admin.characteristictype;

import entities.CharacteristicType;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 * User: Dmitry
 * Date: 7/16/13
 * Time: 5:08 PM
 */
public class CharacteristicTypeModel extends ListDataModel<CharacteristicType> implements SelectableDataModel<CharacteristicType> {

    public CharacteristicTypeModel() {
    }

    public CharacteristicTypeModel(List<CharacteristicType> data) {
        super(data);
    }

    @Override
    public Object getRowKey(CharacteristicType characteristicType) {
        return characteristicType.getIdCharacteristicType();
    }

    @Override
    public CharacteristicType getRowData(String rowKey) {
        List<CharacteristicType> characteristicTypes = (List<CharacteristicType>) getWrappedData();
        for (CharacteristicType characteristicType : characteristicTypes) {
            if (characteristicType.getIdCharacteristicType() == Integer.valueOf(rowKey))
                return characteristicType;
        }
        return null;
    }
}
