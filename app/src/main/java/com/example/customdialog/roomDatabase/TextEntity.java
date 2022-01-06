package com.example.customdialog.roomDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TextEntity {

    @PrimaryKey(autoGenerate = true)  // our primary key which we have autoGenetrated because if we dont do this then at 0th position only next value will come at system
    int id;

    @ColumnInfo(name = "Text")
    String text;


    @ColumnInfo(name = "isFav")
   Boolean isFavorate;



    ///////////////////////////////////// constructor ////////////////////////////////////////////////

    public TextEntity(String text, Boolean isFavorate) {
        this.text = text;
        this.isFavorate = isFavorate;
    }////////////////////////////////////////  getters and setters /////////////////////////////////

    public int getId() {
        return id;
    }                   ////////// id   //////////////////

    public void setId(int id) {
        this.id = id;
    }         /////////  id

    public String getText() {
        return text;
    }            /////////  Text

    public void setText(String text) {
        this.text = text;
    }   /////////  Text

    public Boolean getFavorate() {                      /////////   Fav
        return isFavorate;
    }

    public void setFavorate(Boolean favorate) {         /////////  Fav
        this.isFavorate = favorate;

    }




    @Override
    public String toString() {
        return "TextEntity{" +
                "id=" + id +
                ", Text=" + text +
                '}';
    }


}



//////////////////////////////////////////////////////

/* 1. make an entity class
in that primary key should be there and also column info

2. make a database access object (DAO) interface
for insert/ update /delete/ display -> queries will be there and these are all abstract method
*/