package name;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface TaskInfo {
    String priority();
    String assignedTo();
}

class TaskManager {

    @TaskInfo(priority = "High", assignedTo = "Madhumitha")
    public void completePayrollTask() {
        System.out.println("Completing payroll processing...");
    }

    @TaskInfo(priority = "Low", assignedTo = "Arjun")
    public void backupDatabase() {
        System.out.println("Backing up the database...");
    }
}

public class TaskApp {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Method[] methods = manager.getClass().getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(TaskInfo.class)) {
                TaskInfo info = method.getAnnotation(TaskInfo.class);
                System.out.println("Method: " + method.getName());
                System.out.println("Priority: " + info.priority());
                System.out.println("Assigned To: " + info.assignedTo());
                System.out.println("------------------------");
            }
        }
    }
}

