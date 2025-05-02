package name;
import java.lang.reflect.Method;

public class InvokePrivateMethod {

    static class Calculator {
        private int multiply(int a, int b) {
            return a * b;
        }
    }

    public static void main(String[] args) {
        try {
            Calculator calculator = new Calculator();
            Method multiplyMethod = Calculator.class.getDeclaredMethod("multiply", int.class, int.class);

            multiplyMethod.setAccessible(true);

            int result = (int) multiplyMethod.invoke(calculator, 5, 7);

            System.out.println("Result of multiply(5, 7) = " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

