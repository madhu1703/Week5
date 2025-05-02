package name;
import java.lang.annotation.*;
import java.lang.reflect.Field;

public class MaxLengthValidatorApp {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface MaxLength {
        int value();
    }
    public static class User {
        @MaxLength(10)
        private String username;

        public User(String username) {
            this.username = username;
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(MaxLength.class)) {
                    MaxLength maxLength = field.getAnnotation(MaxLength.class);
                    field.setAccessible(true);
                    try {
                        Object value = field.get(this);
                        if (value instanceof String str) {
                            if (str.length() > maxLength.value()) {
                                throw new IllegalArgumentException(
                                    "Field '" + field.getName() + "' exceeds max length of " + maxLength.value()
                                );
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void show() {
            System.out.println("Username is: " + username);
        }
    }

    public static void main(String[] args) {
        try {
            User user1 = new User("Maddy"); 
            user1.show();

            User user2 = new User("Madhumitha123"); 
            user2.show();

        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }
}
