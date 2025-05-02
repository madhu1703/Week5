package name;
import java.lang.annotation.*;
import java.lang.reflect.Method;

public class ExecutionTimeLoggerApp {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface LogExecutionTime {
    }

    @LogExecutionTime
    public void loadData() throws InterruptedException{
    }

    @LogExecutionTime
    public void processData() throws InterruptedException {
        Thread.sleep(500); 
    }

    public void displayData() throws InterruptedException {
        Thread.sleep(100);
    }

    public static void main(String[] args) {
        ExecutionTimeLoggerApp app = new ExecutionTimeLoggerApp();
        Method[] methods = ExecutionTimeLoggerApp.class.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(LogExecutionTime.class)) {
                try {
                    long start = System.nanoTime();
                    method.invoke(app); 
                    long end = System.nanoTime();
                    long duration = (end - start) / 1_000_000; 

                    System.out.println("Method: " + method.getName());
                    System.out.println("Execution Time: " + duration + " ms");
                    System.out.println("------------------------------");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

