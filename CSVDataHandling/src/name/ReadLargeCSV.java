package name;
import java.io.*;

public class ReadLargeCSV {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\madhumitha\\OneDrive\\Documents\\csv\\Untitled.txt";

        int batchSize = 100;
        int processedCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int batchCounter = 0;
            String header = br.readLine();

            while ((line = br.readLine()) != null) {
                batchCounter++;
                processedCount++;
                if (batchCounter == batchSize) {
                    System.out.println("Processed records: " + processedCount);
                    batchCounter = 0;
                }
            }
            if (batchCounter > 0) {
                System.out.println("Processed records: " + processedCount);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

