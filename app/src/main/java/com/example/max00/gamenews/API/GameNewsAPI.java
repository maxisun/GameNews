package com.example.max00.gamenews.API;

import com.example.max00.gamenews.Classes.News;
import com.example.max00.gamenews.RoomArchitecture.Entity.CategoryEntity;
import com.example.max00.gamenews.RoomArchitecture.Entity.NewsEntity;
import com.example.max00.gamenews.RoomArchitecture.Entity.PlayersEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface GameNewsAPI {

    String BASEURL ="http://gamenewsuca.herokuapp.com";

    @GET("/news")
    Call<List<NewsEntity>> getNews(@Header("Authorization") String autorizazion);

    @FormUrlEncoded
    @POST("/login")
    Call<String> login(@Field("user") String username, @Field("password") String password);

    @GET("/players")
    Call<List<PlayersEntity>> getPlayers(@Header("Authorization") String authorization);

    @GET("/news/type/list")
    Call<List<String>> getCategories(@Header("Authorization") String authorization);
}
