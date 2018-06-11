package com.example.max00.gamenews.API;

import com.example.max00.gamenews.Classes.News;
import com.example.max00.gamenews.RoomArchitecture.Entity.NewsEntity;

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
}
