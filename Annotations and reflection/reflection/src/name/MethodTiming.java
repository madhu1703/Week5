package name;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
class SampleTask {
    public void taskOne() {
        try {
            Thread.sleep(200); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Task One Complete");
    }

    public void taskTwo() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Task Two Complete");
    }
}
public class MethodTiming {
    public static void main(String[] args) throws Exception {
        SampleTask task = new SampleTask();
        Class<?> clazz = task.getClass();

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getParameterCount() == 0 && method.getReturnType() == void.class) {
                System.out.println("\nExecuting: " + method.getName());
                long start = System.nanoTime();

                method.invoke(task);

                long end = System.nanoTime();
                long duration = end - start;
                System.out.println("Execution time: " + duration / 1_000_000.0 + " ms");
            }
        }
    }
}

