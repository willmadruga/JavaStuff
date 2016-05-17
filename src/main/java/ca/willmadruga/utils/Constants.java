package ca.willmadruga.utils;

/**
 * Constants.
 *
 * @author william.madruga
 */
public enum Constants {

    GETTER_PREFIX("get"),
    SETTER_PREFIX("set"),
    CLASS("Class");

    private String value;

    Constants(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
