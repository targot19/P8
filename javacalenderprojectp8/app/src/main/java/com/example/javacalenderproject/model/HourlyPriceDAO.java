package com.example.javacalenderproject.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.Date;
import java.util.List;
@Dao
public interface HourlyPriceDAO {

    @Insert
    void insert(HourlyPrice hourlyPrice);

    @Query("DELETE FROM pricedata WHERE date = :date")
    void delete(Date date);

    @Query("SELECT * FROM pricedata")
    List<HourlyPrice> getAllPrices();

}
