package com.example.customdialog.roomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.customdialog.Fun;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TextDao {

    @Insert
    void insertText(TextEntity text);

    @Update
    void updateText(TextEntity text);

    @Delete
    void deleteText(TextEntity text);

    @Query("SELECT * FROM TextEntity")
    List<TextEntity> displayText();

    @Query("select * from TextEntity where isFav =1")
    List<TextEntity> isFav();
}
