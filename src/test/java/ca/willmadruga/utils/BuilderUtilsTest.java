package ca.willmadruga.utils;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class.
 * 
 * @author william.madruga
 */
public class BuilderUtilsTest {

    String number;
    String serviceProvider;

    @BeforeMethod
    public void setup() {
        number = "604.123.4567";
        serviceProvider = "TELUS";

    }

    @Test
    public void simpleAttributeTest() throws Throwable {

        final Phone phone = (Phone) new BuilderUtils.GenericBuilder().with("number", number).build(Phone.class);

        Assert.assertEquals(number, phone.getNumber());
        ObjectUtils.printToStringFor(phone);

    }

    @Test
    public void inheretedAttributeTest() throws Throwable {

        final MobilePhone phone = (MobilePhone) new BuilderUtils.GenericBuilder().with("number", number).with("serviceProvider", serviceProvider)
            .build(MobilePhone.class);

        Assert.assertEquals(number, phone.getNumber());
        Assert.assertEquals(serviceProvider, phone.getServiceProvider());
        ObjectUtils.printToStringFor(phone);

    }

    @Test
    public void compositionAttributeTest() throws Throwable {

        final String firstName = "William";

        final String lastName = "Madruga";
        final Person owner = (Person) new BuilderUtils.GenericBuilder().with("firstName", firstName).with("lastName", lastName).build(Person.class);

        final MobilePhone phone = (MobilePhone) new BuilderUtils.GenericBuilder().with("number", number).with("serviceProvider", serviceProvider)
            .with("owner", owner).build(MobilePhone.class);

        Assert.assertEquals(firstName, phone.getOwner().getFirstName());
        Assert.assertEquals(lastName, phone.getOwner().getLastName());
        Assert.assertEquals(number, phone.getNumber());
        System.out.println(">>");
        ObjectUtils.printToStringFor(phone);
    }

    @Test(expectedExceptions = NoSuchMethodException.class)
    public void attributeNameNotFoundTest() throws Throwable {
        new BuilderUtils.GenericBuilder().with("num", number).build(Phone.class);
    }

    @Test(expectedExceptions = NoSuchMethodException.class)
    public void incorrectAttributeTypeTest() throws Throwable {
        new BuilderUtils.GenericBuilder().with("number", 778123456L).build(Phone.class);
    }

    public void tryToSetObjectOnAttribute() throws Throwable {
        final Phone phone = (Phone) new BuilderUtils.GenericBuilder()
            .with("owner", new Object())
            .with("number", new Object())
            .build(Phone.class);
        Assert.assertNotNull(phone.getNumber());
        Assert.assertTrue(phone.getNumber().compareToIgnoreCase("") == 0);

        Assert.assertNotNull(phone.getOwner());
        Assert.assertNull(phone.getOwner().getFirstName());
        ObjectUtils.printToStringFor(phone);
    }

}
