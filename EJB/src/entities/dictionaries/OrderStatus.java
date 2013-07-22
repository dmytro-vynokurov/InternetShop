package entities.dictionaries;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/19/13
 * Time: 5:15 PM
 */
public enum OrderStatus {

    //WF stands for WAITING FOR
    WF_PROCESSING("WF_PROCESSING"),
    WF_PAYMENT("WF_PAYMENT"),
    WF_DELIVERY("WF_DELIVERY"),
    CLOSED("CLOSED"),
    CANCELED("CANCELED");

    private final String text;

    private OrderStatus(String text) {
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
