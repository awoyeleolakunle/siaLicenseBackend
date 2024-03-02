package dansarkitechnology.sialicensebackend.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.AttributeConverter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MapConverter implements AttributeConverter<Map<Integer, String>, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Gson gson = new Gson();
    @Override
    public String convertToDatabaseColumn(Map<Integer, String> attribute) {
        return gson.toJson(attribute);
    }

    @Override
    public Map<Integer, String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return new HashMap<>();
        }
        try {
            Type type = new TypeToken<Map<Integer, String>>() {
            }.getType();
            return gson.fromJson(dbData, type);
        } catch (JsonSyntaxException e) {
            // Handle exception
            e.printStackTrace();
            return null;
        }
    }
}