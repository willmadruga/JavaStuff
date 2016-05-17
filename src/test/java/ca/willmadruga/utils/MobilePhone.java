package ca.willmadruga.utils;

/**
 * Created by william.madruga on 2016-05-16.
 */
public class MobilePhone extends Phone {

    private String serviceProvider;

    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(final String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
}
