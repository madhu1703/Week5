package name;
import java.lang.reflect.*;
interface Greeting {
 void sayHello(String name);
 void sayGoodbye();
}
class GreetingImpl implements Greeting {
 public void sayHello(String name) {
     System.out.println("Hello, " + name + "!");
 }

 public void sayGoodbye() {
     System.out.println("Goodbye!");
 }
}

class LoggingInvocationHandler implements InvocationHandler {
 private final Object target;

 public LoggingInvocationHandler(Object target) {
     this.target = target;
 }

 public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
     System.out.println("[LOG] Method called: " + method.getName());
     return method.invoke(target, args);
 }
}
public class LoggingProxy {
 public static void main(String[] args) {
     Greeting original = new GreetingImpl();

     Greeting proxy = (Greeting) Proxy.newProxyInstance(
             Greeting.class.getClassLoader(),
             new Class[]{Greeting.class},
             new LoggingInvocationHandler(original)
     );

     proxy.sayHello("Madhumitha");
     proxy.sayGoodbye();
 }
}

