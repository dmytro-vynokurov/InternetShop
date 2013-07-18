package entities.dictionaries;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/17/13
 * Time: 4:25 PM
 */
public enum PaymentType {
    CASH("CASH"),
    CREDIT("CREDIT");


    private final String text;

    private PaymentType(String text) {
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
