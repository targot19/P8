package com.example.javacalenderproject.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.Date;
import java.util.List;
@Dao
public interface HourlyPriceDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdate(HourlyPrice hourlyPrice);

    @Query("DELETE FROM pricedata WHERE date = :date")
    void delete(Date date);

    @Query("UPDATE pricedata SET price = :price WHERE date = :date")
    void updatePrice(Date date, double price);

    @Query("SELECT * FROM pricedata")
    HourlyPrice[] getAllPrices();

    @Query("DELETE FROM PriceData")
    void deleteAll();

}
