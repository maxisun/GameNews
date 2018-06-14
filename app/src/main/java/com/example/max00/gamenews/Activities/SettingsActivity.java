package com.example.max00.gamenews.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.max00.gamenews.R;

public class SettingsActivity extends AppCompatActivity {

    private Button logout;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initialize();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanPreferences();
            }
        });
    }

    private void initialize(){
        logout = findViewById(R.id.logout_button);
    }

    private void cleanPreferences(){
        preferences = getSharedPreferences("Login_Token", Context.MODE_PRIVATE);
        preferences.edit().clear().apply();
        Intent intent = new Intent(this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
