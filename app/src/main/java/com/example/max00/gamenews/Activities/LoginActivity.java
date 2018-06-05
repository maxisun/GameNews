package com.example.max00.gamenews.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.max00.gamenews.API.GameNewsAPI;
import com.example.max00.gamenews.API.TokenDeserializer;
import com.example.max00.gamenews.Classes.Users;
import com.example.max00.gamenews.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText username,password;
    private Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void initialize(){
        username = findViewById(R.id.username_loginactivity);
        password = findViewById(R.id.password_loginactivity);
        boton = findViewById(R.id.signinbutton_loginactivity);
    }

    private void login(){
        Gson gson = new GsonBuilder().registerTypeAdapter(String.class,new TokenDeserializer()).create();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(GameNewsAPI.BASEURL).addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();
        GameNewsAPI gameNewsAPI = retrofit.create(GameNewsAPI.class);
        final Users users = new Users(username.getText().toString(),password.getText().toString());
        Call<String> call = gameNewsAPI.login(users.getUser(),users.getPassword());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && !response.body().equals("") && !username.getText().equals("") && !password.getText().equals("")) {
                    sharedpreferences(response.body());
                    Toast.makeText(LoginActivity.this,response.body(), Toast.LENGTH_SHORT).show();
                    startativity();
                } else {
                    Toast.makeText(LoginActivity.this, "no response", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    Toast.makeText(LoginActivity.this, "false", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startativity(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void sharedpreferences(String token){
        SharedPreferences sharedPreferences = this.getSharedPreferences("Login_Token", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Token",token);
        //asyncrono, en backgorund
        editor.apply();
    }
}
