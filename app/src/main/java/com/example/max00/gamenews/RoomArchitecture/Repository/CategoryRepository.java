package com.example.max00.gamenews.RoomArchitecture.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.example.max00.gamenews.API.CategoryDeserializer;
import com.example.max00.gamenews.API.GameNewsAPI;
import com.example.max00.gamenews.RoomArchitecture.DAO.CategoryDAO;
import com.example.max00.gamenews.RoomArchitecture.DAO.NewsDAO;
import com.example.max00.gamenews.RoomArchitecture.Entity.CategoryEntity;
import com.example.max00.gamenews.RoomArchitecture.Entity.NewsEntity;
import com.example.max00.gamenews.RoomArchitecture.GameNewsDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryRepository {

    private CategoryDAO categoryDAO;
    private LiveData<List<CategoryEntity>> allcategorieslist;
    private String token;

    public CategoryRepository(Application application){
        GameNewsDatabase db = GameNewsDatabase.getDatabase(application);
        categoryDAO = db.categoryDAO();
        SharedPreferences sharedPreferences = application.getSharedPreferences("Login_Token", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("Token", "");
        fetchCategories();
    }

    public LiveData<List<CategoryEntity>> getAllCategories(){
        return categoryDAO.getAllCategories();
    }

    /*public void insert(CategoryEntity categoryEntity){
        new insertAsyncTask(categoryDAO).execute(categoryEntity);
    }*/

    public void fetchCategories() {
        new CallCategories(token,categoryDAO).execute();
    }


    private static class insertAsyncTask extends AsyncTask<List<CategoryEntity>, Void, Void> {

        private CategoryDAO categoryDAO;

        public insertAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(List<CategoryEntity>... newsEntities) {
            int i;
            List<CategoryEntity> list = newsEntities[0];
            for (i = 0; i < list.size(); i++) {
                CategoryEntity categoryEntity = list.get(i);
                categoryDAO.insertCategory(categoryEntity);
            }
            return null;
        }
    }


    public static class CallCategories extends AsyncTask<Void, Void, Void>{

        private CategoryDAO categoryDAO;
        private String token;

        public CallCategories(String token, CategoryDAO categoryDAO){
            this.token = token;
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(GameNewsAPI.BASEURL).addConverterFactory(GsonConverterFactory.create(new Gson())).build();
            GameNewsAPI gameNewsAPI = retrofit.create(GameNewsAPI.class);
            Call<List<String>> category = gameNewsAPI.getCategories("Beared " + token);
            category.enqueue(new Callback<List<String>>() {
                @Override
                public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                    if(response.isSuccessful()){
                        ArrayList<String> list = (ArrayList<String>) response.body();
                        List<CategoryEntity> categoryEntities = new ArrayList<>();
                        for(int i=0; i<list.size(); i++){
                            CategoryEntity categoryEntity = new CategoryEntity();
                            categoryEntity.setCategory(list.get(i));
                            categoryEntities.add(categoryEntity);
                        }
                        new insertAsyncTask(categoryDAO).execute(categoryEntities);
                    }else{
                        System.out.println("TOSTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOsADFWQR#RWQRFSF");
                    }
                }
                @Override
                public void onFailure(Call<List<String>> call, Throwable t) {
                }
            });
            return null;
        }
    }

}
