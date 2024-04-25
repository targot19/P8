package com.example.javacalenderproject.database;

import androidx.room.TypeConverter;

import com.example.javacalenderproject.database.FamilyMember;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converters {
    @TypeConverter
    public static ArrayList<FamilyMember> fromString(String value) {
        // Converts the JSON string back into a list of familyMember objects, preserving the list type.
        Type listType = new TypeToken<ArrayList<FamilyMember>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<FamilyMember> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<Task> fromTaskString(String value) {
        Type listType = new TypeToken<List<Task>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromTaskList(List<Task> list) {
        Gson gson = new Gson();
        return gson.toJson(list);

    }
}
