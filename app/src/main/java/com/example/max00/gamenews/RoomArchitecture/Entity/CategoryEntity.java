package com.example.max00.gamenews.RoomArchitecture.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "Category_Table")
public class CategoryEntity implements Serializable{

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "name")
    private String category;

    public CategoryEntity() {
    }

    public CategoryEntity(@NonNull String category) {
        this.category = category;
    }

    @NonNull
    public String getCategory() {
        return category;
    }

    public void setCategory(@NonNull String category) {
        this.category = category;
    }
}
