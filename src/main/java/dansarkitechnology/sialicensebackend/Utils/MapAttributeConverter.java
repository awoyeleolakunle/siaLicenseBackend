package dansarkitechnology.sialicensebackend.Utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.HashMap;
import java.util.Map;


@Converter(autoApply = true)
public class MapAttributeConverter  implements AttributeConverter<Map<Integer, String>, String> {

    @Override
    public String convertToDatabaseColumn(Map<Integer, String> attribute) {
        System.out.println("I'm the attribute : " + attribute);
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }


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

        Map<Integer, String> map = new HashMap<>();
        dbData = dbData.substring(1, dbData.length() - 1);
        String[] entries = dbData.split(",");
        for (String entry : entries) {
            String[] keyValue = entry.split(":");
            int key = Integer.parseInt(keyValue[0].trim().replace("\"", ""));
            String value = keyValue[1].trim().replace("\"", "");
            map.put(key, value);
        }
        System.out.println("I'm the map : "+ map );
        return map;
    }
    }
