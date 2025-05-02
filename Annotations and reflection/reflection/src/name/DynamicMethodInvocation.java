package name;
import java.lang.reflect.Method;
import java.util.Scanner;

public class DynamicMethodInvocation {

    static class MathOperations {
        public int add(int a, int b) {
            return a + b;
        }

        public int subtract(int a, int b) {
            return a - b;
        }

        public int multiply(int a, int b) {
            return a * b;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            MathOperations mathOps = new MathOperations();

            System.out.print("Enter method name (add, subtract, multiply): ");
            String methodName = scanner.nextLine();

            System.out.print("Enter first integer: ");
            int a = scanner.nextInt();

            System.out.print("Enter second integer: ");
            int b = scanner.nextInt();
            Method method = MathOperations.class.getMethod(methodName, int.class, int.class);
            Object result = method.invoke(mathOps, a, b);

            System.out.println("Result: " + result);

        } catch (NoSuchMethodException e) {
            System.out.println("No such method found.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
