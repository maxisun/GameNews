package com.example.max00.gamenews.Classes;

import java.io.Serializable;

public class News implements Serializable{
    private int image;
    private String titulo;
    private String subtitulo;

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
}
