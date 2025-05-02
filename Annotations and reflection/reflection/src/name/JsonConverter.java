package name;
import java.lang.reflect.Field;

public class JsonConverter {

    public static String toJson(Object obj) {
        if (obj == null) {
            return "null";
        }

        Class<?> clazz = obj.getClass();
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");

        Field[] fields = clazz.getDeclaredFields();
        boolean first = true;

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                Object value = field.get(obj);

                if (!first) {
                    jsonBuilder.append(", ");
                } else {
                    first = false;
                }

                jsonBuilder.append("\"").append(field.getName()).append("\": ");

                if (value == null) {
                    jsonBuilder.append("null");
                } else if (value instanceof String) {
                    jsonBuilder.append("\"").append(value).append("\"");
                } else {
                    jsonBuilder.append(value);
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }
}
