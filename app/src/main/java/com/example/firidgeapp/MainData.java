package com.example.firidgeapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "table_name")
public class MainData implements Serializable
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "text")
    private String text;
    private String text2;
    private String text3;
    private String text4;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    public String getText()
    {
        return text;
    }
    public void setText(String text)
    {
        this.text = text;
    }

    public String getText2()
    {
        return text2;
    }
    public void setText2(String text2)
    {
        this.text2 = text2;
    }


    public String getText3()
    {
        return text3;
    }
    public void setText3(String text3)
    {
        this.text3 = text3;
    }


    public String getText4()
    {
        return text4;
    }
    public void setText4(String text4){  this.text4 = text4; }
}