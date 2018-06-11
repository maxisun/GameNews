package com.example.max00.gamenews.RoomArchitecture.Repository;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.example.max00.gamenews.API.GameNewsAPI;
import com.example.max00.gamenews.Adapters.NewsAdapter;
import com.example.max00.gamenews.Classes.News;
import com.example.max00.gamenews.RoomArchitecture.DAO.NewsDAO;
import com.example.max00.gamenews.RoomArchitecture.Entity.NewsEntity;
import com.example.max00.gamenews.RoomArchitecture.GameNewsDatabase;
import com.example.max00.gamenews.RoomArchitecture.ViewModel.NewsViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsRepository {

    private NewsDAO newsDAO;
    private LiveData<List<NewsEntity>> mAllNews;
    private String token;

    public NewsRepository(Application application) {
        GameNewsDatabase db = GameNewsDatabase.getDatabase(application);
        newsDAO = db.newsDAO();
        mAllNews = newsDAO.getAllNews();
        SharedPreferences sharedPreferences = application.getSharedPreferences("Login_Token", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("Token", "");
        fetchnews();
    }

    public LiveData<List<NewsEntity>> getmAllNews() {
        return mAllNews;
    }

    public void insert(List<NewsEntity> news) {
        new insertAsyncTask(newsDAO).execute(news);
    }

    public void fetchnews() {
        new fetchNews(token,newsDAO).execute();
    }


    private static class insertAsyncTask extends AsyncTask<List<NewsEntity>, Void, Void> {

        private NewsDAO newsDAO;

        public insertAsyncTask(NewsDAO newsDAO) {
            this.newsDAO = newsDAO;
        }

        @Override
        protected Void doInBackground(List<NewsEntity>... newsEntities) {
            int i;
            List<NewsEntity> list = newsEntities[0];
            for (i = 0; i < list.size(); i++) {
                NewsEntity newsEntity = list.get(i);
                newsDAO.insertNews(newsEntity);
            }
            return null;
        }
    }

    public static class fetchNews extends AsyncTask<Void, Void, Void> {

        private NewsDAO newsDAO;
        private String token;

        public fetchNews(String token, NewsDAO newsDAO) {
            this.newsDAO = newsDAO;
            this.token = token;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(GameNewsAPI.BASEURL).addConverterFactory(GsonConverterFactory.create(new Gson())).build();
            GameNewsAPI gameNewsAPI = retrofit.create(GameNewsAPI.class);
            Call<List<NewsEntity>> news = gameNewsAPI.getNews("Beared " + token);
            news.enqueue(new Callback<List<NewsEntity>>() {
                @Override
                public void onResponse(Call<List<NewsEntity>> call, Response<List<NewsEntity>> response) {
                    if (response.isSuccessful()) {
                        System.out.println("cargando");
                        System.out.println(token);
                        List<NewsEntity> list = response.body();
                        Collections.reverse(list);
                        new insertAsyncTask(newsDAO).execute(list);
                    } else {
                        System.out.println("fallo");
                    }
                }
                @Override
                public void onFailure(Call<List<NewsEntity>> call, Throwable t) {
                    System.out.println("on failure");
                }
            });
            return null;
        }
    }
}
    /*public void getallnewsrequest(String token){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(GameNewsAPI.BASEURL).addConverterFactory(GsonConverterFactory.create(new Gson())).build();
        GameNewsAPI gameNewsAPI = retrofit.create(GameNewsAPI.class);
        Call<List<NewsEntity>> news = gameNewsAPI.getNews("Beared " + token);
        news.enqueue(new Callback<List<NewsEntity>>() {
            @Override
            public void onResponse(Call<List<NewsEntity>> call, Response<List<NewsEntity>> response) {
                if(response.isSuccessful()){
                    System.out.println("cargando");
                    List<NewsEntity> list = response.body();
                    Collections.reverse(list);
                    new insertAsyncTask(newsDAO).execute(list);
                }else {
                    System.out.println("fallo");
                }
            }
            @Override
            public void onFailure(Call<List<NewsEntity>> call, Throwable t) {
                System.out.println("on failure");
            }
        });
    }*/


