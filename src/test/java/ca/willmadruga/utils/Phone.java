package ca.willmadruga.utils;

/**
 * Created by william.madruga on 2016-05-16.
 */
public class Phone {

    private Person owner;

    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(final Person owner) {
        this.owner = owner;
    }
}
