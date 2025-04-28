package name;

import java.io.*;

public class ReadCSV {
    public static void main(String[] args) {
        String line;
        String csvFile = "C:\\Users\\madhumitha\\OneDrive\\Documents\\students.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();

            while ((line = br.readLine()) != null) {
              
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

               
                if (data.length < 4) {
                    continue;
                }

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int age = Integer.parseInt(data[2]);
                int marks = Integer.parseInt(data[3]);

                System.out.println("Student ID : " + id);
                System.out.println("Name       : " + name);
                System.out.println("Age        : " + age);
                System.out.println("Marks      : " + marks);
                System.out.println("---------------------------");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
