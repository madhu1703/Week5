package name;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Base64;

public class CsvEncryptDecrypt {

    private static final String ALGORITHM = "AES";

    private static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(128); 
        return keyGen.generateKey();
    }

    private static SecretKeySpec getKeySpec() {
        
        byte[] keyBytes = "1234567890abcdef".getBytes(); 
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

    public static String encrypt(String plainText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String cipherText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    public static void writeEncryptedCSV(String filePath, SecretKey key) throws Exception {
        try (FileWriter writer = new FileWriter(filePath)) {
            
            writer.append("EmployeeID,Name,Department,Salary,Email\n");

            String[][] employees = {
                    {"101", "Alice", "HR", "50000", "alice@example.com"},
                    {"102", "Bob", "IT", "60000", "bob@example.com"},
                    {"103", "Charlie", "Finance", "70000", "charlie@example.com"}
            };

            for (String[] emp : employees) {
                String encryptedSalary = encrypt(emp[3], key);
                String encryptedEmail = encrypt(emp[4], key);

                writer.append(String.join(",", emp[0], emp[1], emp[2], encryptedSalary, encryptedEmail)).append("\n");
            }
            System.out.println("CSV with encrypted data written successfully.");
        }
    }

    public static void readAndDecryptCSV(String filePath, SecretKey key) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); 
            System.out.println(line);   

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",", -1);
                String employeeId = fields[0];
                String name = fields[1];
                String department = fields[2];
                String decryptedSalary = decrypt(fields[3], key);
                String decryptedEmail = decrypt(fields[4], key);

                System.out.printf("%s, %s, %s, %s, %s%n", employeeId, name, department, decryptedSalary, decryptedEmail);
            }
        }
    }

    public static void main(String[] args) {
        try {
            SecretKeySpec key = getKeySpec();

            String csvFile = "employees_encrypted.csv";

            writeEncryptedCSV(csvFile, key);
            System.out.println("\nReading and decrypting CSV content:\n");
            readAndDecryptCSV(csvFile, key);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
