package dto.characteristic;

import entities.Characteristic;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 * User: Dmitry
 * Date: 7/16/13
 * Time: 5:08 PM
 */
public class CharacteristicModel extends ListDataModel<Characteristic> implements SelectableDataModel<Characteristic> {

    public CharacteristicModel() {
    }

    public CharacteristicModel(List<Characteristic> data) {
        super(data);
    }

    @Override
    public Object getRowKey(Characteristic characteristic) {
        return characteristic.getIdCharacteristic();
    }

    @Override
    public Characteristic getRowData(String rowKey) {
        List<Characteristic> characteristics = (List<Characteristic>) getWrappedData();
        for (Characteristic characteristic : characteristics) {
            if (characteristic.getIdCharacteristic() == Integer.valueOf(rowKey))
                return characteristic;
        }
        return null;
    }
}
