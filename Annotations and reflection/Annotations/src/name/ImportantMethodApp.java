package name;
import java.lang.annotation.*;
import java.lang.reflect.Method;

public class ImportantMethodApp {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ImportantMethod {
        String level() default "HIGH";
    }

    @ImportantMethod(level = "MEDIUM")
    public void performBackup() {
        System.out.println("Performing backup...");
    }

    @ImportantMethod 
    public void generateReport() {
        System.out.println("Generating report...");
    }

    public void logActivity() {
        System.out.println("Logging activity...");
    }

    public static void main(String[] args) {
        ImportantMethodApp app = new ImportantMethodApp();
        Method[] methods = app.getClass().getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(ImportantMethod.class)) {
                ImportantMethod imp = method.getAnnotation(ImportantMethod.class);
                System.out.println("Important Method: " + method.getName());
                System.out.println("Level: " + imp.level());
                System.out.println("----------------------------");
            }
        }
    }
}

