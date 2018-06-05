package com.example.max00.gamenews.Classes;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable,Comparable<News>{

    private String id;
    private int image;
    private String titulo;
    private String subtitulo;
    private String game;
    private String created_date;
    private String coverImage;
    private Date createdDate;
    private String description;

    public News() {
    }

    public News(int image, String titulo, String subtitulo) {
        this.image = image;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(@NonNull News o) {
        return 0;
    }
}
