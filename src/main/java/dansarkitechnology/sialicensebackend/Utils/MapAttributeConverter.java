package dansarkitechnology.sialicensebackend.Utils;

import jakarta.persistence.AttributeConverter;

import java.util.HashMap;
import java.util.Map;

public class MapAttributeConverter  implements AttributeConverter<Map<Integer, String>, String> {

    @Override
    public String convertToDatabaseColumn(Map<Integer, String> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        // For simplicity, let's assume JSON serialization
        // You can use libraries like Jackson or Gson for more robust serialization
        return "{" +
                attribute.entrySet().stream()
                        .map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
                        .reduce((s1, s2) -> s1 + "," + s2)
                        .orElse("") +
                "}";
    }

    @Override
    public Map<Integer, String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return new HashMap<>();
        }
        // For simplicity, let's assume JSON deserialization
        // You can use libraries like Jackson or Gson for more robust deserialization
        Map<Integer, String> map = new HashMap<>();
        dbData = dbData.substring(1, dbData.length() - 1); // Remove curly braces
        String[] entries = dbData.split(",");
        for (String entry : entries) {
            String[] keyValue = entry.split(":");
            int key = Integer.parseInt(keyValue[0].replace("\"", ""));
            String value = keyValue[1].replace("\"", "");
            map.put(key, value);
        }
        return map;
    }
    }
