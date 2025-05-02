package name;
import java.lang.reflect.Field;
import java.util.Map;

public class CustomObjectMapper {

    public static <T> T toObject(Class<T> clazz, Map<String, Object> properties) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();

            for (Map.Entry<String, Object> entry : properties.entrySet()) {
                String fieldName = entry.getKey();
                Object value = entry.getValue();

                try {
                    Field field = clazz.getDeclaredField(fieldName);
                    field.setAccessible(true);

                    field.set(instance, value);
                } catch (NoSuchFieldException e) {
                    System.out.println("Warning: No field named '" + fieldName + "' in class " + clazz.getSimpleName());
                }
            }

            return instance;

        } catch (Exception e) {
            throw new RuntimeException("Failed to map properties to object: " + e.getMessage(), e);
        }
    }
}

