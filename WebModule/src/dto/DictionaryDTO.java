package dto;

import entities.dictionaries.DeliveryType;
import entities.dictionaries.PaymentType;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import java.io.Serializable;

/**
 * User: Dmitry
 * Date: 7/22/13
 * Time: 7:11 AM
 */
@ManagedBean(name = "dictionaryDTO")
@RequestScoped
public class DictionaryDTO implements Serializable {

    public SelectItem[] getPaymentTypes() {
        SelectItem[] items = new SelectItem[PaymentType.values().length];
        int i = 0;
        for (PaymentType g : PaymentType.values()) {
            items[i++] = new SelectItem(g, g.getText());
        }
        return items;
    }

    public SelectItem[] getDeliveryTypes() {
        SelectItem[] items = new SelectItem[DeliveryType.values().length];
        int i = 0;
        for (DeliveryType g : DeliveryType.values()) {
            items[i++] = new SelectItem(g, g.getText());
        }
        return items;
    }
}
