package name;
import java.util.ArrayList;


public class SuppressWarningsProblem {


    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ArrayList list = new ArrayList(); 
        list.add("Hello");
        list.add("World");
        ArrayList<String> stringList = list;


        for (String s : stringList) {
            System.out.println(s);
        }
    }
}

