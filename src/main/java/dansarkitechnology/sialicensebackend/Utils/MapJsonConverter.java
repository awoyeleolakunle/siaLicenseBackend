package dansarkitechnology.sialicensebackend.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MapJsonConverter implements AttributeConverter<Map, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(Map attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            // Handle serialization error
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map<Integer, String> convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return new HashMap<>();
        }

        try {
            return objectMapper.readValue(dbData, Map.class);
        } catch (IOException e) {
            // Handle deserialization error
            e.printStackTrace();
            return null;
        }
    }
}
