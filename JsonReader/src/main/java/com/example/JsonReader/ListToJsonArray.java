package com.example.JsonReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;

class User {
    private String name;
    private String email;

    public User(String name, String email) { this.name = name; this.email = email; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}

public class ListToJsonArray {
    public static void main(String[] args) throws Exception {
        List<User> users = Arrays.asList(
            new User("Alice", "alice@example.com"),
            new User("Bob", "bob@example.com")
        );

        ObjectMapper mapper = new ObjectMapper();
        String jsonArray = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(users);

        System.out.println(jsonArray);
    }
}

