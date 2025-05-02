package name;
import java.lang.annotation.*;
import java.lang.reflect.Method;

public class TodoApp {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Todo {
        String task();
        String assignedTo();
        String priority() default "MEDIUM";
    }

    @Todo(task = "Implement login functionality", assignedTo = "Madhumitha", priority = "HIGH")
    public void loginFeature() {
        
    }

    @Todo(task = "Add password encryption", assignedTo = "Arjun")
    public void encryptPasswords() {
       
    }

    @Todo(task = "Write unit tests", assignedTo = "Priya", priority = "LOW")
    public void writeTests() {
       
    }

    public void finishedFeature() {
        System.out.println("This feature is done.");
    }

    public static void main(String[] args) {
        Method[] methods = TodoApp.class.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Todo.class)) {
                Todo todo = method.getAnnotation(Todo.class);
                System.out.println("Method: " + method.getName());
                System.out.println("Task: " + todo.task());
                System.out.println("Assigned To: " + todo.assignedTo());
                System.out.println("Priority: " + todo.priority());
                System.out.println("------------------------------");
            }
        }
    }
}
