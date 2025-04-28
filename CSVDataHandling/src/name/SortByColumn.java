package name;
import java.io.*;
import java.util.*;

class Employee {
    String name;
    String department;
    double salary;

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
}

public class SortByColumn {
    public static void main(String[] args) {
        String fileName = "C:\\\\Users\\\\madhumitha\\\\OneDrive\\\\Documents\\\\csv\\\\employees.csv"; 
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine(); 

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    String department = parts[1].trim();
                    double salary = Double.parseDouble(parts[2].trim());

                    employees.add(new Employee(name, department, salary));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary format in file.");
            return;
        }
        employees.sort((e1, e2) -> Double.compare(e2.salary, e1.salary));
        System.out.println("Top 5 Highest-Paid Employees:");
        for (int i = 0; i < Math.min(5, employees.size()); i++) {
            Employee e = employees.get(i);
            System.out.println(e.name + " | " + e.department + " | $" + e.salary);
        }
    }
}
