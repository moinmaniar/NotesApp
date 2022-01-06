package com.example.customdialog.roomDatabase;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

//@Entity(foreignKeys = @ForeignKey(entity = TextEntity.class,
//        parentColumns = "isFav",
//        childColumns = "isMyFav",
//        onDelete = CASCADE))

@Entity
public class FavorateEntity {

    @PrimaryKey
    public int Favid;

    @NonNull
    public boolean isMyFav;  // this is the child column


}
