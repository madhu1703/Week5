package name;
import java.lang.annotation.*;
import java.lang.reflect.Field;

public class CustomJsonSerializer {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface JsonField {
        String name();
    }

    public static class User {
        @JsonField(name = "user_name")
        private String username;

        @JsonField(name = "user_age")
        private int age;

        @JsonField(name = "email_address")
        private String email;

        public User(String username, int age, String email) {
            this.username = username;
            this.age = age;
            this.email = email;
        }
    }

    public static String toJson(Object obj) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");

        Field[] fields = obj.getClass().getDeclaredFields();

        boolean firstField = true;
        for (Field field : fields) {
            if (field.isAnnotationPresent(JsonField.class)) {
                field.setAccessible(true);
                JsonField annotation = field.getAnnotation(JsonField.class);
                String jsonKey = annotation.name();

                try {
                    Object value = field.get(obj);
                    if (!firstField) {
                        jsonBuilder.append(", ");
                    } else {
                        firstField = false;
                    }

                    jsonBuilder.append("\"").append(jsonKey).append("\": ");

                    if (value instanceof String) {
                        jsonBuilder.append("\"").append(value).append("\"");
                    } else {
                        jsonBuilder.append(value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }

    public static void main(String[] args) {
        User user = new User("Madhumitha", 25, "madhumitha@example.com");
        String json = toJson(user);
        System.out.println(json);
    }
}
