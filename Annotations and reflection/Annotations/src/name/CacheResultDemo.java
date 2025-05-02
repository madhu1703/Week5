package name;
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CacheResultDemo {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface CacheResult {
    }
    static class CacheKey {
        private final Method method;
        private final Object[] args;

        public CacheKey(Method method, Object[] args) {
            this.method = method;
            this.args = args != null ? args.clone() : new Object[0];
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CacheKey)) return false;
            CacheKey other = (CacheKey) o;
            return method.equals(other.method) && Arrays.deepEquals(args, other.args);
        }

        @Override
        public int hashCode() {
            return Objects.hash(method, Arrays.deepHashCode(args));
        }
    }

    public static class ExpensiveService {

        @CacheResult
        public long fibonacci(int n) {
            if (n <= 1) return n;
            try {
                
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static class CacheProxy {

        private final Object target;
        private final Map<CacheKey, Object> cache = new HashMap<>();

        public CacheProxy(Object target) {
            this.target = target;
        }

        public Object invoke(String methodName, Object... args) throws Exception {
            Method method = findMethod(target.getClass(), methodName, args);
            if (method == null) {
                throw new NoSuchMethodException("Method " + methodName + " not found");
            }

            if (method.isAnnotationPresent(CacheResult.class)) {
                CacheKey key = new CacheKey(method, args);
                if (cache.containsKey(key)) {
                    System.out.println("Returning cached result for method " + methodName + " with args " + Arrays.toString(args));
                    return cache.get(key);
                } else {
                    Object result = method.invoke(target, args);
                    cache.put(key, result);
                    return result;
                }
            } else {
                return method.invoke(target, args);
            }
        }

        private Method findMethod(Class<?> clazz, String methodName, Object[] args) {
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.getName().equals(methodName) && method.getParameterCount() == args.length) {
                    boolean matches = true;
                    Class<?>[] paramTypes = method.getParameterTypes();
                    for (int i = 0; i < paramTypes.length; i++) {
                        if (args[i] != null && !paramTypes[i].isAssignableFrom(args[i].getClass())) {
                            matches = false;
                            break;
                        }
                    }
                    if (matches) return method;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        ExpensiveService service = new ExpensiveService();
        CacheProxy proxy = new CacheProxy(service);

        System.out.println("Computing fibonacci(10) first time:");
        long start = System.currentTimeMillis();
        Object result1 = proxy.invoke("fibonacci", 10);
        long end = System.currentTimeMillis();
        System.out.println("Result: " + result1 + " (Time taken: " + (end - start) + "ms)");

        System.out.println("\nComputing fibonacci(10) second time (should be cached):");
        start = System.currentTimeMillis();
        Object result2 = proxy.invoke("fibonacci", 10);
        end = System.currentTimeMillis();
        System.out.println("Result: " + result2 + " (Time taken: " + (end - start) + "ms)");

        System.out.println("\nComputing fibonacci(8) (not cached yet):");
        start = System.currentTimeMillis();
        Object result3 = proxy.invoke("fibonacci", 8);
        end = System.currentTimeMillis();
        System.out.println("Result: " + result3 + " (Time taken: " + (end - start) + "ms)");
    }
}
