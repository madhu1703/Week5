package com.example.JsonReader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

public class ReadJsonPrintAll {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        try {
          
            JsonNode rootNode = mapper.readTree(new File("data.json"));
            printJson("", rootNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printJson(String prefix, JsonNode node) {
        if (node.isObject()) {
            Iterator<Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Entry<String, JsonNode> entry = fields.next();
                printJson(prefix + entry.getKey() + " -> ", entry.getValue());
            }
        } else if (node.isArray()) {
            int index = 0;
            for (JsonNode arrayElement : node) {
                printJson(prefix + "[" + index + "] -> ", arrayElement);
                index++;
            }
        } else {
            System.out.println(prefix + node.asText());
        }
    }
}
