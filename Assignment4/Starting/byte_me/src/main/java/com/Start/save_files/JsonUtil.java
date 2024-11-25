package com.Start.save_files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT) // Pretty-print JSON
            .enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS); // Order maps for readability

    public static <T> void saveToJson(String filePath, T data) {
        try {
            objectMapper.writeValue(new File(filePath), data);
            System.out.println("Data successfully saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    public static <T> T loadFromJson(String filePath, Class<T> valueType) {
        try {
            return objectMapper.readValue(new File(filePath), valueType);
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
            return null;
        }
    }
}
