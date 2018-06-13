package com.example.max00.gamenews.RoomArchitecture.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.example.max00.gamenews.API.GameNewsAPI;
import com.example.max00.gamenews.RoomArchitecture.DAO.PlayersDAO;
import com.example.max00.gamenews.RoomArchitecture.Entity.PlayersEntity;
import com.example.max00.gamenews.RoomArchitecture.GameNewsDatabase;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayersRepository {

    private PlayersDAO playersDAO;
    private LiveData<List<PlayersEntity>> lolplayers;
    private LiveData<List<PlayersEntity>> overwatchplayers;
    private LiveData<List<PlayersEntity>> csgoplayers;
    private String token;

    public PlayersRepository(Application application){
        GameNewsDatabase db = GameNewsDatabase.getDatabase(application);
        SharedPreferences sharedPreferences = application.getSharedPreferences("Login_Token", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("Token", "");
        playersDAO = db.playersDAO();
        lolplayers = playersDAO.getLolPlayers("lol");
        overwatchplayers = playersDAO.getOverwatchPlayers("overwatch");
        csgoplayers = playersDAO.getCsgoPlayers("csgo");
        fetchplayers();
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

    public void insert(List<PlayersEntity> players){
        new insertAsyncTask(playersDAO).execute(players);
    }

    public void fetchplayers(){
        new fetchPlayers(playersDAO,token).execute();
    }

    private static class insertAsyncTask extends AsyncTask<List<PlayersEntity>,Void,Void>{

        private PlayersDAO playersDAO;

        public insertAsyncTask(PlayersDAO playersDAO){
            this.playersDAO = playersDAO;
        }

        @Override
        protected Void doInBackground(List<PlayersEntity>... playersEntities) {
            int i;
            List<PlayersEntity> list = playersEntities[0];
            for (i=0; i<list.size(); i++){
                PlayersEntity playersEntity = list.get(i);
                playersDAO.insertPlayers(playersEntity);
            }
            return null;
        }
    }

    private static class fetchPlayers extends AsyncTask<Void,Void,Void>{

        private PlayersDAO playersDAO;
        private String token;

        public fetchPlayers(PlayersDAO playersDAO, String token){
            this.playersDAO = playersDAO;
            this.token = token;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(GameNewsAPI.BASEURL).addConverterFactory(GsonConverterFactory.create(new Gson())).build();
            GameNewsAPI gameNewsAPI = retrofit.create(GameNewsAPI.class);
            Call<List<PlayersEntity>> news = gameNewsAPI.getPlayers("Beared " + token);
            news.enqueue(new Callback<List<PlayersEntity>>() {
                @Override
                public void onResponse(Call<List<PlayersEntity>> call, Response<List<PlayersEntity>> response) {
                    if (response.isSuccessful()) {
                        System.out.println("cargando");
                        System.out.println(token);
                        List<PlayersEntity> list = response.body();
                        new PlayersRepository.insertAsyncTask(playersDAO).execute(list);
                    } else {
                        System.out.println("fallo");
                    }
                }
                @Override
                public void onFailure(Call<List<PlayersEntity>> call, Throwable t) {
                    System.out.println("on failure");
                }
            });
            return null;
        }
    }
}
