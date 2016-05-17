
package ca.willmadruga.utils;

import java.lang.reflect.Method;

/**
 * Addons to a java object.
 * 
 * @author william.madruga
 *
 */
public class ObjectUtils {

    /**
     * Overrides toString() method on given object to print out all its attributes.
     */
    public static void printToStringFor(final Object object) {

        System.out.println(object.getClass().getSimpleName() + " [");

        Class<?> currentClass = object.getClass();
        Class<?> superClass = null;
        do {
            try {
                final Method[] methods = currentClass.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.getName().startsWith(Constants.GETTER_PREFIX.getValue())) {
                        final String field = extractFieldNameFrom(method);
                        if (field != null && field.compareTo(Constants.CLASS.getValue()) != 0) {
                            final Object getterValue = method.invoke(object);
                            if (isAnObjectReference(getterValue)) {
                                System.out.println("\t" + field + " = ");
                                printToStringFor(getterValue);
                            } else {
                                System.out.println("\t" + field + " = " + getterValue);
                            }
                        }
                    }
                }
            } catch (final Exception e) {
                // any problems sir?
            } finally {
                superClass = currentClass.getSuperclass();
                currentClass = superClass;
            }
        } while (superClass != null);

        System.out.println("]");
    }

    /**
     * Formats method name removing three initial chars supposed to be 'get'. Returns method name with message if not a getter.
     */
    private static String extractFieldNameFrom(final Method method) {
        try {
            if (method.getName().startsWith(Constants.GETTER_PREFIX.getValue())) {
                return method.getName().substring(3, method.getName().length());
            }
        } catch (final Exception e) {
            // any problems sir?
        }
        return null;
    }

    /**
     * Returns true if it is an object reference, false otherwise.
     */
    private static boolean isAnObjectReference(final Object object) {
        // TODO: need a more reliable way of doing that.
        try {
            final String[] split = object.toString().split("@");
            return (split != null && split.length > 1);
        } catch (Exception e) {
            // any problems sir?
        }
        return false;
    }

}
