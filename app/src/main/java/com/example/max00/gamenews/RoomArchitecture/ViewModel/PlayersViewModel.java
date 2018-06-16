package com.example.max00.gamenews.RoomArchitecture.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.max00.gamenews.RoomArchitecture.Entity.PlayersEntity;
import com.example.max00.gamenews.RoomArchitecture.Repository.PlayersRepository;

import java.util.List;

public class PlayersViewModel extends AndroidViewModel {

    private PlayersRepository playersRepository;

    public PlayersViewModel(Application application){
        super(application);
        playersRepository = new PlayersRepository(application);
    }

    public void insert(List<PlayersEntity> players){
        playersRepository.insert(players);
    }

    public LiveData<List<PlayersEntity>> getCategorizedPlayers(String game){
        return playersRepository.getCategorizedPlayers(game);
    }
}
