package com.example.JsonReader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class JsonToXmlConverter {
    public static void main(String[] args) throws Exception {
        String json = """
            {
              "person": {
                "name": "Alice",
                "age": 30,
                "email": "alice@example.com"
              }
            }
            """;

        ObjectMapper jsonMapper = new ObjectMapper();
        XmlMapper xmlMapper = new XmlMapper();

        JsonNode tree = jsonMapper.readTree(json);

        String xml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(tree);

        System.out.println(xml);
    }
}
