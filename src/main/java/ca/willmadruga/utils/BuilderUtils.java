package ca.willmadruga.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Builder implementation for java beans. Attributes are set via reflection and should match the bean attribute name.
 * 
 * @author william.madruga
 */

public class BuilderUtils {

    private Object object;

    /**
     * Constructor providing target class and the builder. Will assign attributes to target class object through reflection.
     */
    public BuilderUtils(final Class klass, final GenericBuilder builder) throws Throwable {

        object = klass.newInstance();

        for (final Map.Entry<String, Object> attrEntry : builder.attributes.entrySet()) {

            final String setterName = buildSetterName(attrEntry);
            final Method attributeSetter = object.getClass().getMethod(setterName, attrEntry.getValue().getClass());
            attributeSetter.invoke(object, attrEntry.getValue());

        }

    }

    private String buildSetterName(final Map.Entry<String, Object> attrEntry) {
        final StringBuilder setterName = new StringBuilder();
        setterName.append(Constants.SETTER_PREFIX.getValue()).append(attrEntry.getKey().toUpperCase().charAt(0));
        setterName.append(attrEntry.getKey().subSequence(1, attrEntry.getKey().length()));
        return setterName.toString();
    }

    /**
     * Gathers attributes for target class object.
     */
    public static class GenericBuilder {

        final Map<String, Object> attributes = new HashMap<>();

        /**
         * Generic attribute builder.
         */
        public GenericBuilder with(final String attribute, final Object value) {
            this.attributes.put(attribute, value);
            return this;
        }

        /**
         * Builds an object from given class and attributes set through the type builder.
         */
        public Object build(final Class klass) throws Throwable {
            final BuilderUtils genericBuilder = new BuilderUtils(klass, this);
            return genericBuilder.object;
        }

    }

}
