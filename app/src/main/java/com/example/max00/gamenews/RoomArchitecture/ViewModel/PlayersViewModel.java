package com.example.max00.gamenews.RoomArchitecture.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.max00.gamenews.RoomArchitecture.Entity.PlayersEntity;
import com.example.max00.gamenews.RoomArchitecture.Repository.PlayersRepository;

import java.util.List;

public class PlayersViewModel extends AndroidViewModel {

    private PlayersRepository playersRepository;
    private LiveData<List<PlayersEntity>> lolplayers;
    private LiveData<List<PlayersEntity>> overwatchplayers;
    private LiveData<List<PlayersEntity>> csgoplayers;

    public PlayersViewModel(Application application){
        super(application);
        playersRepository = new PlayersRepository(application);
        lolplayers = playersRepository.getLolplayers();
        overwatchplayers = playersRepository.getOverwatchplayers();
        csgoplayers = playersRepository.getCsgoplayers();
    }

    public void insert(List<PlayersEntity> players){
        playersRepository.insert(players);
    }

    public LiveData<List<PlayersEntity>> getLolplayers() {
        return lolplayers;
    }

    public LiveData<List<PlayersEntity>> getOverwatchplayers() {
        return overwatchplayers;
    }

    public LiveData<List<PlayersEntity>> getCsgoplayers() {
        return csgoplayers;
    }
}
