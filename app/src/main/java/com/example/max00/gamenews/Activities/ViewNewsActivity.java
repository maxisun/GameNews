package com.example.max00.gamenews.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.max00.gamenews.R;
import com.example.max00.gamenews.RoomArchitecture.Entity.NewsEntity;
import com.squareup.picasso.Picasso;

public class ViewNewsActivity extends AppCompatActivity {

    private ImageView imagen;
    private TextView titulo,body,game,fecha;
    private NewsEntity newsEntity;
    private String failed = "Failed to load...";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_news);
        initialize();
        setNewsUI();
    }

    private void initialize(){
        imagen = findViewById(R.id.Imagen_View_News_Activity);
        titulo = findViewById(R.id.TextView_Titulo_View_News_Activity);
        body = findViewById(R.id.TextView_Body_View_News_Activity);
        game = findViewById(R.id.TextView_Game_View_News_Activity);
        fecha = findViewById(R.id.TextView_Fecha_View_News_Activity);
    }

    private void setNewsUI(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        newsEntity = (NewsEntity) bundle.getSerializable("NewsInformation");

        if (!(newsEntity.getCoverImage() == null)) {
            Picasso.with(getApplicationContext()).load(newsEntity.getCoverImage()).error(R.drawable.chino).into(imagen);
        } else {
            Picasso.with(getApplicationContext()).load(R.drawable.chino).error(R.drawable.chino).into(imagen);
        }

        if (newsEntity.getTitle()==null || newsEntity.getTitle().isEmpty()){
            titulo.setText(failed);
        }else {
            titulo.setText(newsEntity.getTitle());
        }

        if (newsEntity.getBody()==null || newsEntity.getBody().isEmpty()){
            body.setText(failed);
        }else {
            body.setText(newsEntity.getBody());
        }

        if (newsEntity.getGame()==null || newsEntity.getGame().isEmpty()){
            game.setText(failed);
        }else {
            game.setText(newsEntity.getGame());
        }

        if (newsEntity.getCreated_date()==null || newsEntity.getCreated_date().isEmpty()){
            fecha.setText(failed);
        }else {
            fecha.setText(newsEntity.getCreated_date());
        }
    }

}
