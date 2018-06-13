package com.example.max00.gamenews.RoomArchitecture;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.max00.gamenews.RoomArchitecture.DAO.NewsDAO;
import com.example.max00.gamenews.RoomArchitecture.DAO.PlayersDAO;
import com.example.max00.gamenews.RoomArchitecture.Entity.NewsEntity;
import com.example.max00.gamenews.RoomArchitecture.Entity.PlayersEntity;

@Database(entities = {NewsEntity.class, PlayersEntity.class}, exportSchema = false, version = 1)
public abstract class GameNewsDatabase extends RoomDatabase {

    public abstract NewsDAO newsDAO();
    public abstract PlayersDAO playersDAO();

    private static GameNewsDatabase INSTANCE;

    public static GameNewsDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (GameNewsDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),GameNewsDatabase.class,"Game_News_Database").build();
                }
            }
        }
        return INSTANCE;
    }
}
