package com.example.max00.gamenews.RoomArchitecture.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.max00.gamenews.RoomArchitecture.Entity.PlayersEntity;

import java.util.List;

@Dao
public interface PlayersDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlayers(PlayersEntity... players);

    @Query("SELECT * FROM Players_Table WHERE game = :game")
    LiveData<List<PlayersEntity>> getCategorizedPlayers(String game);

    @Query("DELETE FROM Players_Table")
    void deleteAllPlayers();
}
