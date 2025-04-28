package name;
import java.io.*;
import java.util.*;

public class DetectDuplicates {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\madhumitha\\OneDrive\\Documents\\students.csv"; 

        Set<String> ids = new HashSet<>();       
        Set<String> duplicateIds = new HashSet<>(); 

        List<String> duplicateRecords = new ArrayList<>(); 

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String header = br.readLine(); 
            System.out.println("Header: " + header);

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); 
                if (parts.length > 0) {
                    String id = parts[0].trim();

                    if (ids.contains(id)) {
                        duplicateIds.add(id);
                        duplicateRecords.add(line);
                    } else {
                        ids.add(id);
                    }
                }
            }

            if (duplicateIds.isEmpty()) {
                System.out.println("No duplicate records found.");
            } else {
                System.out.println("Duplicate records:");
                for (String record : duplicateRecords) {
                    System.out.println(record);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

