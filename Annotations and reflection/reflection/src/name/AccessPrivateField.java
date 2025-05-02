package name;
import java.lang.reflect.Field;

public class AccessPrivateField {

    static class Person {
        private int age;

        public Person(int age) {
            this.age = age;
        }
        public int getAge() {
            return age;
        }
    }

    public static void main(String[] args) {
        try {
            Person person = new Person(25);
            System.out.println("Original age (using getter): " + person.getAge());

            Field ageField = Person.class.getDeclaredField("age");

            ageField.setAccessible(true);

            ageField.setInt(person, 40);

            int updatedAge = ageField.getInt(person);
            System.out.println("Updated age (using reflection): " + updatedAge);

            System.out.println("Updated age (using getter): " + person.getAge());

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
