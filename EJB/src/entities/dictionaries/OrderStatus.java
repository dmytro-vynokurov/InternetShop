package entities.dictionaries;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/19/13
 * Time: 5:15 PM
 */
public enum OrderStatus {
    WAITING_FOR_PROCESSING("WAITING_FOR_PROCESSING"),
    PROCESSING("PROCESSING"),
    WAITING_FOR_PAYMENT("WAITING_FOR_PAYMENT"),
    WAITING_FOR_DELIVERY("WAITING_FOR_DELIVERY"),
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
