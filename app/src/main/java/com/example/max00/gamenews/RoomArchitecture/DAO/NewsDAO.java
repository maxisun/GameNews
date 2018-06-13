package com.example.max00.gamenews.RoomArchitecture.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.max00.gamenews.RoomArchitecture.Entity.NewsEntity;

import java.util.List;

@Dao
public interface NewsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNews(NewsEntity... news);

    /*@Query("SELECT * FROM News_table")
    LiveData<List<NewsEntity>> getAllNews();*/

    @Query("SELECT * FROM News_table ORDER BY createdDate DESC")
    LiveData<List<NewsEntity>> getAllNews();

    @Query("SELECT * FROM News_table WHERE game = :game ORDER BY createdDate DESC")
    LiveData<List<NewsEntity>> getSpecifiedNew(String game);

    @Query("SELECT * FROM News_table WHERE game = :game ORDER BY createdDate DESC")
    LiveData<List<NewsEntity>> getSpecifiedOverwatch(String game);

    @Query("SELECT * FROM News_table WHERE game = :game ORDER BY createdDate DESC")
    LiveData<List<NewsEntity>> getSpecifiedCSGO(String game);
}
