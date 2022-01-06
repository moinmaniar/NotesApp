package com.example.customdialog.roomDatabase;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public class UtilityHelper {
    public static TextDatabase getDatabase(Context context)
    {
        TextDatabase textDatabase = Room.databaseBuilder(context,TextDatabase.class, "TextDatabase")
                .allowMainThreadQueries().build();

        return textDatabase;
    }
}
