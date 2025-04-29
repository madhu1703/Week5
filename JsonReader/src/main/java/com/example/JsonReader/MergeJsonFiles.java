package com.example.JsonReader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;

public class MergeJsonFiles {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        File file1 = new File("file1.json");
        File file2 = new File("file2.json");

        JsonNode json1 = mapper.readTree(file1);
        JsonNode json2 = mapper.readTree(file2);

        if (!(json1 instanceof ObjectNode) || !(json2 instanceof ObjectNode)) {
            System.out.println("Both JSON inputs must be JSON objects.");
            return;
        }

        ObjectNode merged = ((ObjectNode) json1).deepCopy();

        json2.fields().forEachRemaining(entry -> merged.set(entry.getKey(), entry.getValue()));

        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(merged));
    }
}
