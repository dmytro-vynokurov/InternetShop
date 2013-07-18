package entities.dictionaries;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/17/13
 * Time: 3:32 PM
 */

public enum Color {
    BLACK("BLACK"),
    RED("RED"),
    BLUE("BLUE"),
    YELLOW("YELLOW"),
    GREEN("GREEN"),
    VIOLET("VIOLET"),
    PINK("PINK"),
    GREY("GREY"),
    SILVER("SILVER"),
    METALLIC("METALLIC"),
    WHITE("WHITE"),
    BROWN("BROWN");

    private final String text;

    private Color(String text) {
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
