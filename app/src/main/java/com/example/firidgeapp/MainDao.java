package com.example.firidgeapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao
{
    @Insert(onConflict = REPLACE)
    void insert(MainData mainData);

    @Delete
    void delete(MainData mainData);

    @Delete
    void reset(List<MainData> mainData);

    @Query("UPDATE table_name SET text = :sText , text3 = :sText3 , text2 = :sText2 , text4 = :sText4 WHERE ID = :sID")
    void update(int sID, String sText,String sText3,String sText2,String sText4);

    @Query("SELECT * FROM table_name order by text3 asc") // D - day 내림차순
    List<MainData> getAll();
}