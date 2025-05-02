package name;
import java.lang.annotation.*;
import java.lang.reflect.Method;

public class RoleBasedAccessApp {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface RoleAllowed {
        String value(); 
    }
    public static class AdminPanel {

        @RoleAllowed("ADMIN")
        public void deleteUserAccount() {
            System.out.println("User account deleted.");
        }

        @RoleAllowed("USER")
        public void viewProfile() {
            System.out.println("Viewing user profile.");
        }

        public void generalInfo() {
            System.out.println("Accessing general information.");
        }
    }

    public static void main(String[] args) {
        String currentUserRole = "USER"; 
        AdminPanel panel = new AdminPanel();

        Method[] methods = AdminPanel.class.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(RoleAllowed.class)) {
                RoleAllowed roleAllowed = method.getAnnotation(RoleAllowed.class);
                if (roleAllowed.value().equals(currentUserRole)) {
                    try {
                        System.out.println("Access Granted to method: " + method.getName());
                        method.invoke(panel);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Access Denied to method: " + method.getName());
                }
            } else {
                try {
                    System.out.println("No role required. Accessing: " + method.getName());
                    method.invoke(panel);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("------------------------------");
        }
    }
}
