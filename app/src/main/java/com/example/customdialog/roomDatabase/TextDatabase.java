package com.example.customdialog.roomDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TextEntity.class, FavorateEntity.class},version = 1)
public abstract class TextDatabase extends RoomDatabase {

    public abstract  TextDao getTextDao();  // query daoClass  justMethodName
}


/* note::   for select * from tablename; you have to make object of interface(in our case it is TextDao)
*  and for that we have to make object of our database because TextDao is there in TextDatabase class
* and this(TextDatabase) is abstract so we have to create the object of TextDatabase and it is there
*  in UtilityHelper class
*   */