package name;

import java.util.*;
import java.io.*;

public class ReadAndSearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter employee name to search: ");
        String searchName = sc.nextLine().trim().toLowerCase();

        String filePath = "C:\\Users\\madhumitha\\OneDrive\\Documents\\csv\\employees.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4) {
                    String name = data[1].trim();     
                    String department = data[2].trim(); 
                    String salary = data[3].trim();     

                    if (name.equalsIgnoreCase(searchName)) {
                        System.out.println("Department: " + department);
                        System.out.println("Salary: " + salary);
                        break; 
                    }
                    else{
                        System.out.println("Employee named '" + searchName + "' not found.");
                        break;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        sc.close();
    }
}
