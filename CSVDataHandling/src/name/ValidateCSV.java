package name;
import java.io.*;
import java.util.regex.*;

public class ValidateCSV {
    public static void main(String[] args) {
    	String fileName = "C:\\Users\\madhumitha\\OneDrive\\Documents\\csv\\contacts.csv";
        String line;
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String phoneRegex = "^\\d{10}$";

        Pattern emailPattern = Pattern.compile(emailRegex);
        Pattern phonePattern = Pattern.compile(phoneRegex);

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (lineNumber == 1) continue;

                String[] parts = line.split(",");

                if (parts.length != 3) {
                    System.out.println("Line " + lineNumber + ": Invalid column count.");
                    continue;
                }

                String name = parts[0].trim();
                String email = parts[1].trim();
                String phone = parts[2].trim();

                boolean valid = true;

                if (!emailPattern.matcher(email).matches()) {
                    System.out.println("Line " + lineNumber + ": Invalid email format - " + email);
                    valid = false;
                }

                if (!phonePattern.matcher(phone).matches()) {
                    System.out.println("Line " + lineNumber + ": Invalid phone number - " + phone);
                    valid = false;
                }

                if (valid) {
                    System.out.println("Valid: " + name + " | " + email + " | " + phone);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
