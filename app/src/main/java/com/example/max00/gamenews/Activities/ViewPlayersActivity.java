package com.example.max00.gamenews.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.max00.gamenews.R;
import com.example.max00.gamenews.RoomArchitecture.Entity.NewsEntity;
import com.example.max00.gamenews.RoomArchitecture.Entity.PlayersEntity;
import com.squareup.picasso.Picasso;

public class ViewPlayersActivity extends AppCompatActivity {

    private ImageView imagen;
    private TextView nombre,biografia;
    private PlayersEntity players;
    private String failed = "Failed to load...";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_players);
        initialize();
        setPlayersUI();
    }

    private void initialize(){
        imagen = findViewById(R.id.Imagen_View_Players_Activity);
        nombre = findViewById(R.id.TextView_Nombre_View_Players_Activity);
        biografia = findViewById(R.id.TextView_Biografia_View_Players_Activity);
    }
    private void setPlayersUI(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        players = (PlayersEntity) bundle.getSerializable("PlayersInformation");

        if (!(players.getAvatar() == null)) {
            Picasso.with(getApplicationContext()).load(players.getAvatar()).error(R.drawable.chino).into(imagen);
        } else {
            Picasso.with(getApplicationContext()).load(R.drawable.chino).error(R.drawable.chino).into(imagen);
        }

        if (players.getName()==null || players.getName().isEmpty()){
            nombre.setText(failed);
        }else {
            nombre.setText(players.getName());
        }

        if (players.getBiografia()==null || players.getBiografia().isEmpty()){
            biografia.setText(failed);
        }else {
            biografia.setText(players.getBiografia());
        }
    }
}
