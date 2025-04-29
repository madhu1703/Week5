package com.example.JsonReader;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class EmailValidation {
    public static void main(String[] args) {
        String schemaStr = """
        {
          "$schema": "http://json-schema.org/draft-07/schema#",
          "type": "object",
          "properties": {
            "email": {
              "type": "string",
              "format": "email"
            }
          },
          "required": ["email"]
        }
        """;

        String jsonStrValid = "{\"email\":\"user@example.com\"}";
        String jsonStrInvalid = "{\"email\":\"invalid-email\"}";

        JSONObject rawSchema = new JSONObject(new JSONTokener(schemaStr));
        Schema schema = SchemaLoader.load(rawSchema);

        validateJson(schema, jsonStrValid);   
        validateJson(schema, jsonStrInvalid); 
    }

    private static void validateJson(Schema schema, String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            schema.validate(jsonObject);  
            System.out.println(jsonData + " is valid.");
        } catch (Exception e) {
            System.out.println(jsonData + " is NOT valid: " + e.getMessage());
        }
    }
}

