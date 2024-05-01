package com.example.javacalenderproject.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    public static List<TaskTemplate> fromTaskString(String value) {
        Type listType = new TypeToken<List<TaskTemplate>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromTaskList(List<TaskTemplate> list) {
        Gson gson = new Gson();
        return gson.toJson(list);

    }

// fatter ikke den her kode
    @TypeConverter
    public static Long fromDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    @TypeConverter
    public static LocalDateTime toDateTime(Long dbValue) {
        if (dbValue == 0) {
            return null;
        }
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(dbValue), ZoneId.systemDefault());
    }
}
