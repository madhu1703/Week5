package com.example.JsonReader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class FilterJsonByAge {
    public static void main(String[] args) throws Exception {
        String json = "["
            + "{\"name\":\"Alice\",\"age\":24},"
            + "{\"name\":\"Bob\",\"age\":30},"
            + "{\"name\":\"Charlie\",\"age\":28}"
            + "]";

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = (ArrayNode) mapper.readTree(json);

        ArrayNode filteredArray = mapper.createArrayNode();

        for (JsonNode node : arrayNode) {
            if (node.has("age") && node.get("age").asInt() > 25) {
                filteredArray.add(node);
            }
        }

        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(filteredArray));
    }
}
