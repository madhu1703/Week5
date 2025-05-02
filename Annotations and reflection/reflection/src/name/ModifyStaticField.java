package name;
import java.lang.reflect.Field;

public class ModifyStaticField {

    static class Configuration {
        private static String API_KEY = "INITIAL_KEY";

        public static String getApiKey() {
            return API_KEY;
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Original API_KEY: " + Configuration.getApiKey());

            Field apiKeyField = Configuration.class.getDeclaredField("API_KEY");

            apiKeyField.setAccessible(true);

            apiKeyField.set(null, "NEW_SECRET_API_KEY");

            System.out.println("Modified API_KEY: " + Configuration.getApiKey());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
