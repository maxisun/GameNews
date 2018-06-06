package com.example.max00.gamenews.API;

import com.example.max00.gamenews.Classes.News;
import com.example.max00.gamenews.Classes.Users;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface GameNewsAPI {

    String BASEURL ="http://gamenewsuca.herokuapp.com";

    @GET("/news")
    Call<List<News>> getNews(@Header("Authorization") String autorizazion);

    @FormUrlEncoded
    @POST("/login")
    Call<String> login(@Field("user") String username, @Field("password") String password);
}
