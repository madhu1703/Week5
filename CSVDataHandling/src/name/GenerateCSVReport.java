package name;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateCSVReport {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/your_database"; // change your_database
        String username = "your_username";
        String password = "your_password";

        String csvFilePath = "employees_report.csv";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);
             Statement statement = connection.createStatement();
             FileWriter csvWriter = new FileWriter(csvFilePath)) {

            String sql = "SELECT employee_id, name, department, salary FROM employees";
            ResultSet resultSet = statement.executeQuery(sql);

           
            csvWriter.append("Employee ID,Name,Department,Salary\n");

            
            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                double salary = resultSet.getDouble("salary");

                csvWriter.append(employeeId + "," + escapeSpecialCharacters(name) + "," + escapeSpecialCharacters(department) + "," + salary + "\n");
            }

            System.out.println("CSV file was created successfully at " + csvFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String escapeSpecialCharacters(String data) {
        String escapedData = data;
        if (data.contains(",") || data.contains("\"") || data.contains("\n")) {
            escapedData = data.replace("\"", "\"\"");
            escapedData = "\"" + escapedData + "\"";
        }
        return escapedData;
    }
}
