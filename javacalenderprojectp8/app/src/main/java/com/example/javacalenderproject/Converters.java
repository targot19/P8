package com.example.javacalenderproject;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {
    @TypeConverter
    public static ArrayList<familyMember> fromString(String value) {
        // Converts the JSON string back into a list of familyMember objects, preserving the list type.
        Type listType = new TypeToken<ArrayList<familyMember>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<familyMember> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
