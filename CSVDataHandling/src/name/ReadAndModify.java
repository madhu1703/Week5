package name;

import java.io.*;

public class ReadAndModify {
public static void main(String[] args)
{
	{
        String line;
        String filePath = "C:\\Users\\madhumitha\\OneDrive\\Documents\\csv\\employees.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); 
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; 
                }

                String[] data = line.split(",");
                if (data.length >= 4) {
                    String name = data[1].trim();
                    String department = data[2].trim();
                    double salary = Double.parseDouble(data[3].trim());

                    if (department.equalsIgnoreCase("Engineering")) {
                        double bonus = salary * 0.1;
                        salary += bonus;
                        System.out.printf("Updated salary  " + name + salary +"\n");
                    } 
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
    }
}
