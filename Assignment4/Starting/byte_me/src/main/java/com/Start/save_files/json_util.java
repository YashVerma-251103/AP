package com.Start.save_files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.FieldAttributes;
import com.google.gson.ExclusionStrategy;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;

public class json_util {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    // private static final Gson gson = new GsonBuilder().setPrettyPrinting().addSerializationExclusionStrategy(new ExclusionStrategy() {
    //     @Override
    //     public boolean shouldSkipField(FieldAttributes f) {
    //         return f.hasModifier(java.lang.reflect.Modifier.PRIVATE);
    //     }

    //     @Override
    //     public boolean shouldSkipClass(Class<?> clazz) {
    //         return false;
    //     }
    // }).create();

    public static <T> void save_to_json(String file_path, T data) {
        try (FileWriter writer = new FileWriter(file_path)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T load_from_json(String file_path, Type type) {
        try (FileReader reader = new FileReader(file_path)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
