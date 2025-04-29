package com.example.JsonReader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class FilterUsersByAge {
    public static void main(String[] args) throws Exception {
        String json = "["
            + "{\"name\":\"Alice\", \"age\":24},"
            + "{\"name\":\"Bob\", \"age\":30},"
            + "{\"name\":\"Charlie\", \"age\":28}"
            + "]";

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode users = (ArrayNode) mapper.readTree(json);

        ArrayNode filteredUsers = mapper.createArrayNode();

        for (JsonNode user : users) {
            if (user.has("age") && user.get("age").asInt() > 25) {
                filteredUsers.add(user);
            }
        }

        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(filteredUsers));
    }
}
