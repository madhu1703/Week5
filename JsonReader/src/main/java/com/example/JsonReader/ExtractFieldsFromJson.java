package com.example.JsonReader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class ExtractFieldsFromJson {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode rootNode = mapper.readTree(new File("data.json"));

            for (JsonNode node : rootNode) {
                String name = node.get("name").asText();
                String email = node.get("email").asText();

                System.out.println("Name: " + name + ", Email: " + email);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

