package name;
import java.io.*;
import java.util.*;

public class Merge {
    public static void main(String[] args) {
        String file1 = "C:\\Users\\madhumitha\\OneDrive\\Documents\\csv\\student1.csv";
        String file2 = "C:\\Users\\madhumitha\\OneDrive\\Documents\\csv\\student2.csv";
        String outputFile = "C:\\Users\\madhumitha\\OneDrive\\Documents\\csv\\Untitled.txt";

        Map<String, String[]> map1 = new HashMap<>();
      
        Map<String, String[]> map2 = new HashMap<>();

        try {
            BufferedReader br1 = new BufferedReader(new FileReader(file1));
            String line = br1.readLine(); 
            while ((line = br1.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    String age = parts[2].trim();
                    map1.put(id, new String[]{name, age});
                }
            }
            br1.close();
            BufferedReader br2 = new BufferedReader(new FileReader(file2));
            line = br2.readLine(); 
            while ((line = br2.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String id = parts[0].trim();
                    String marks = parts[1].trim();
                    String grade = parts[2].trim();
                    map2.put(id, new String[]{marks, grade});
                }
            }
            br2.close();

           
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
           
            bw.write("ID,Name,Age,Marks,Grade");
            bw.newLine();
            for (String id : map1.keySet()) {
                String[] info1 = map1.get(id);
                String[] info2 = map2.get(id);

                String name = info1[0];
                String age = info1[1];
                String marks = (info2 != null) ? info2[0] : "";
                String grade = (info2 != null) ? info2[1] : "";

                bw.write(id + "," + name + "," + age + "," + marks + "," + grade);
                bw.newLine();
            }
            for (String id : map2.keySet()) {
                if (!map1.containsKey(id)) {
                    String[] info2 = map2.get(id);
                    bw.write(id + ",,,"
                            + info2[0] + "," + info2[1]);
                    bw.newLine();
                }
            }

            bw.close();
            System.out.println("Merged CSV file created: " + outputFile);

        } catch (IOException e) {
            System.out.println("Error processing files: " + e.getMessage());
        }
    }
}

