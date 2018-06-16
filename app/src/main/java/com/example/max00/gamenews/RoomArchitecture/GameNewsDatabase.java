package com.example.max00.gamenews.RoomArchitecture;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.max00.gamenews.RoomArchitecture.DAO.CategoryDAO;
import com.example.max00.gamenews.RoomArchitecture.DAO.NewsDAO;
import com.example.max00.gamenews.RoomArchitecture.DAO.PlayersDAO;
import com.example.max00.gamenews.RoomArchitecture.Entity.CategoryEntity;
import com.example.max00.gamenews.RoomArchitecture.Entity.NewsEntity;
import com.example.max00.gamenews.RoomArchitecture.Entity.PlayersEntity;

@Database(entities = {NewsEntity.class, PlayersEntity.class, CategoryEntity.class}, exportSchema = false, version = 1)
public abstract class GameNewsDatabase extends RoomDatabase {

    public abstract NewsDAO newsDAO();
    public abstract PlayersDAO playersDAO();
    public abstract CategoryDAO categoryDAO();

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

    public static GameNewsDatabase getINSTANCE() {
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                /*@Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }*/


            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final NewsDAO mDao;
        private final PlayersDAO playersDAO;

        PopulateDbAsync(GameNewsDatabase db) {
            mDao = db.newsDAO();
            playersDAO = db.playersDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAllNews();
            playersDAO.deleteAllPlayers();
            return null;
        }
    }
}
