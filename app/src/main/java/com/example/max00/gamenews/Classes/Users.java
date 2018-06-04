package com.example.max00.gamenews.Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class Users {

    @SerializedName("_id")
    private String id;
    @SerializedName("created_date")
    private String date;
    @SerializedName("__v")
    private int version;
    @SerializedName("favoriteNews")
    private List<News> favouriteNews;
    //extras
    private String user;
    private String password;
    private String Token;

    public Users(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<News> getFavouriteNews() {
        return favouriteNews;
    }

    public void setFavouriteNews(List<News> favouriteNews) {
        this.favouriteNews = favouriteNews;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
