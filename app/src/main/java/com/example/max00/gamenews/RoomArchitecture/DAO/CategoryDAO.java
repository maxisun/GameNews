package com.example.max00.gamenews.RoomArchitecture.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.example.max00.gamenews.RoomArchitecture.Entity.CategoryEntity;
import java.util.List;

@Dao
public interface CategoryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(CategoryEntity... category);

    @Query("SELECT * FROM Category_Table ORDER BY category")
    LiveData<List<CategoryEntity>> getAllCategories();
}
