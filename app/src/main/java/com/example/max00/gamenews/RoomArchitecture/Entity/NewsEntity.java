package com.example.max00.gamenews.RoomArchitecture.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "News_table")
public class NewsEntity implements Serializable{

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String _id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "coverImage")
    private String coverImage;

    @ColumnInfo(name = "createdDate")
    private String created_date;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "body")
    private String body;

    @ColumnInfo(name = "game")
    private String game;

    public NewsEntity(@NonNull String id, String title, String coverImage, String createdDate, String description, String body, String game) {
        this._id = id;
        this.title = title;
        this.coverImage = coverImage;
        this.created_date = createdDate;
        this.description = description;
        this.body = body;
        this.game = game;
    }

    public NewsEntity(){

    }

    @NonNull
    public String getId() {
        return _id;
    }

    public void setId(@NonNull String id) {
        this._id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getCreated_date() {
        return created_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }
}
