package com.example.JsonReader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MergeJsonObjects {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode json1 = mapper.readTree("{\"name\":\"Alice\", \"email\":\"alice@example.com\"}");
            JsonNode json2 = mapper.readTree("{\"age\":25, \"city\":\"New York\"}");

           
            ObjectNode merged = (ObjectNode) json1;
            merged.setAll((ObjectNode) json2);

   
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(merged));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
