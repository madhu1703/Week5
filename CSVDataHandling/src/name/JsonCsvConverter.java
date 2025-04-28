package name;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class JsonCsvConverter {
    static class Student {
        public int id;
        public String name;
        public int age;
        public String grade;
        public Student() {}
        
        public Student(int id, String name, int age, String grade) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.grade = grade;
        }
    }
    public static void jsonToCsv(String jsonFilePath, String csvFilePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        List<Student> students = mapper.readValue(new File(jsonFilePath), new TypeReference<List<Student>>() {});

        try (FileWriter csvWriter = new FileWriter(csvFilePath)) {
           
            csvWriter.append("id,name,age,grade\n");

            for (Student s : students) {
                csvWriter.append(s.id + "," + escapeSpecialCharacters(s.name) + "," + s.age + "," + s.grade + "\n");
            }
        }
        System.out.println("Converted JSON to CSV successfully.");
    }

    public static void csvToJson(String csvFilePath, String jsonFilePath) throws IOException {
        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean headerSkipped = false;

            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    
                    headerSkipped = true;
                    continue;
                }
                String[] fields = line.split(",", -1);

                int id = Integer.parseInt(fields[0]);
                String name = unescapeSpecialCharacters(fields[1]);
                int age = Integer.parseInt(fields[2]);
                String grade = fields[3];

                students.add(new Student(id, name, age, grade));
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonFilePath), students);

        System.out.println("Converted CSV back to JSON successfully.");
    }
    private static String escapeSpecialCharacters(String data) {
        String escapedData = data;
        if (data.contains(",") || data.contains("\"") || data.contains("\n")) {
            escapedData = data.replace("\"", "\"\"");
            escapedData = "\"" + escapedData + "\"";
        }
        return escapedData;
    }
    private static String unescapeSpecialCharacters(String data) {
        if (data.startsWith("\"") && data.endsWith("\"")) {
            data = data.substring(1, data.length() - 1).replace("\"\"", "\"");
        }
        return data;
    }

    public static void main(String[] args) {
        String jsonInput = "students.json";
        String csvOutput = "students.csv";
        String jsonOutput = "students_converted.json";

        try {
            jsonToCsv(jsonInput, csvOutput);
            csvToJson(csvOutput, jsonOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
