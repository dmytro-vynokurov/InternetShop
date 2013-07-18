package entities.dictionaries;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/17/13
 * Time: 4:22 PM
 */
public enum DeliveryType {
    POSTAL("POSTAL"),
    COURIER("COURIER"),
    PICK_UP("PICK_UP");

    private final String text;

    private DeliveryType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
