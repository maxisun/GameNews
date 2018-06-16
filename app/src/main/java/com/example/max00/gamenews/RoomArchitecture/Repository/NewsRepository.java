package com.example.max00.gamenews.RoomArchitecture.Repository;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;
import com.example.max00.gamenews.API.GameNewsAPI;
import com.example.max00.gamenews.Activities.LoginActivity;
import com.example.max00.gamenews.RoomArchitecture.DAO.NewsDAO;
import com.example.max00.gamenews.RoomArchitecture.Entity.NewsEntity;
import com.example.max00.gamenews.RoomArchitecture.GameNewsDatabase;
import com.google.gson.Gson;
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
    private Application application;

    public NewsRepository(Application application) {
        GameNewsDatabase db = GameNewsDatabase.getDatabase(application);
        this.application = application;
        newsDAO = db.newsDAO();
        mAllNews = newsDAO.getAllNews();
        SharedPreferences sharedPreferences = application.getSharedPreferences("Login_Token", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("Token", "");
        fetchnews();
    }


    public LiveData<List<NewsEntity>> getmAllNews() {
        return mAllNews;
    }

    public LiveData<List<NewsEntity>> getCategorizedNews(String game){
        return newsDAO.getCategorizedNews(game);
    }

    public void insert(List<NewsEntity> news) {
        new insertAsyncTask(newsDAO).execute(news);
    }

    public void fetchnews() {
        new fetchNews(token,newsDAO,application).execute();
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
        private static Context context;
        private Application application;

        public fetchNews(String token, NewsDAO newsDAO, Application application) {
            this.newsDAO = newsDAO;
            this.token = token;
            this.application = application;
        }

        public static void setContext(Context context) {
            fetchNews.context = context;
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
                        List<NewsEntity> list = response.body();
                        Collections.reverse(list);
                        new insertAsyncTask(newsDAO).execute(list);
                    } else {
                        Toast.makeText(context,"Su sesion ha expirado: ERROR "+response.code(),Toast.LENGTH_LONG).show();
                        cleanPreferences();
                    }
                }
                @Override
                public void onFailure(Call<List<NewsEntity>> call, Throwable t) {
                    Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        }

        public void cleanPreferences(){
            SharedPreferences preferences = application.getSharedPreferences("Login_Token",Context.MODE_PRIVATE);
            preferences.edit().clear().apply();
            Intent intent = new Intent(context,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        }
    }
}


