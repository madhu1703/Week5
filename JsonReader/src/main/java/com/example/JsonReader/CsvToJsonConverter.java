package com.example.JsonReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.StringReader;
import java.util.List;

public class CsvToJsonConverter {
    public static void main(String[] args) throws Exception {
        String csvData = """
            name,age,email
            Alice,30,alice@example.com
            Bob,25,bob@example.com
            Charlie,35,charlie@example.com
            """;

        CSVReader csvReader = new CSVReader(new StringReader(csvData));
        List<String[]> rows = csvReader.readAll();

        if (rows.isEmpty()) {
            System.out.println("No data found.");
            return;
        }

        String[] headers = rows.get(0);
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode jsonArray = mapper.createArrayNode();

        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);
            var jsonObject = mapper.createObjectNode();

            for (int j = 0; j < headers.length; j++) {
                jsonObject.put(headers[j], row[j]);
            }

            jsonArray.add(jsonObject);
        }

        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonArray));
    }
}
