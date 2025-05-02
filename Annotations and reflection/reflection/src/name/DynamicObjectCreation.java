package name;
import java.lang.reflect.Constructor;

public class DynamicObjectCreation {
    static class Student {
        private String name;
        private int rollNo;

        public Student() {
            this.name = "Default Name";
            this.rollNo = 0;
        }

        public Student(String name, int rollNo) {
            this.name = name;
            this.rollNo = rollNo;
        }

        @Override
        public String toString() {
            return "Student{name='" + name + "', rollNo=" + rollNo + "}";
        }
    }

    public static void main(String[] args) {
        try {
            Class<?> studentClass = Class.forName("DynamicObjectCreation$Student");
            Constructor<?> constructor = studentClass.getConstructor();
            Object studentObj = constructor.newInstance();

            System.out.println("Created object: " + studentObj);
            Constructor<?> paramConstructor = studentClass.getConstructor(String.class, int.class);
            Object studentObj2 = paramConstructor.newInstance("Alice", 101);

            System.out.println("Created object with parameters: " + studentObj2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

