package name;
import java.io.*;
import java.util.*;
class Student {
    private int id;
    private String name;
    private String department;
    private double cgpa;

    public Student(int id, String name, String department, double cgpa) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.cgpa = cgpa;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + department + " | CGPA: " + cgpa;
    }
}
public class ConvertCSV {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\madhumitha\\OneDrive\\Documents\\csv\\students.csv"; 
        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNum = 0;

            while ((line = br.readLine()) != null) {
                lineNum++;
                if (lineNum == 1) continue;

                String[] parts = line.split(",");

                if (parts.length != 4) {
                    System.out.println("Skipping invalid row at line " + lineNum);
                    continue;
                }

                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String department = parts[2].trim();
                    double cgpa = Double.parseDouble(parts[3].trim());

                    students.add(new Student(id, name, department, cgpa));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format at line " + lineNum + ": " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        System.out.println("List of Students:");
        for (Student s : students) {
            System.out.println(s);
        }
    }
}

