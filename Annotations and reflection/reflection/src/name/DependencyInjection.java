package name;
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Inject {
}

class SimpleDIContainer {
    private final Map<Class<?>, Object> instances = new HashMap<>();

    public <T> void register(Class<T> clazz) throws Exception {
        Object instance = createInstance(clazz);
        instances.put(clazz, instance);
    }

    private <T> T createInstance(Class<T> clazz) throws Exception {
        T obj = clazz.getDeclaredConstructor().newInstance();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);

                Class<?> fieldType = field.getType();
                Object dependency = instances.get(fieldType);
                if (dependency == null) {
                    dependency = createInstance(fieldType);
                    instances.put(fieldType, dependency);
                }

                field.set(obj, dependency);
            }
        }

        return obj;
    }
    public <T> T getBean(Class<T> clazz) {
        return clazz.cast(instances.get(clazz));
    }
}
class Service {
    public void serve() {
        System.out.println("Service is serving...");
    }
}

class Client {
    @Inject
    private Service service;

    public void doWork() {
        service.serve();
    }
}
public class DependencyInjection {
    public static void main(String[] args) throws Exception {
        SimpleDIContainer container = new SimpleDIContainer();

        container.register(Service.class);
        container.register(Client.class);

        Client client = container.getBean(Client.class);
        client.doWork();
    }
}
