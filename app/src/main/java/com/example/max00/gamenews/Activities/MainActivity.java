package com.example.max00.gamenews.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.max00.gamenews.Classes.News;
import com.example.max00.gamenews.Fragments.NewsFragment;
import com.example.max00.gamenews.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mactionBarDrawerToggle;
    private NavigationView navigationView;
    private ArrayList<News> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //inicializando atributos
        initialize();
        FillLolis();
        mactionBarDrawerToggle = new ActionBarDrawerToggle(this,mdrawerLayout,R.string.open,R.string.close);
        mdrawerLayout.addDrawerListener(mactionBarDrawerToggle);
        //Synchronize the state of the drawer indicator/affordance with the linked DrawerLayout.
        mactionBarDrawerToggle.syncState();
        //Set whether home should be displayed as an "up" affordance.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean fragtransac = false;
                android.support.v4.app.Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.news_drawermenu_ID:
                        fragment = NewsFragment.newInstance(list);
                        fragtransac = true;
                        break;
                }
                if(fragtransac){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_main,fragment).commit();
                    item.setChecked(true);
                    getSupportActionBar().setTitle(item.getTitle());
                    mdrawerLayout.closeDrawers();
                }
                return true;
            }
        });


    }
    private void initialize(){
        mdrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
    }

    //funcion para hacer que funcione el boton para mostrar el drawerlayout
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mactionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void FillLolis(){
        list = new ArrayList<>();
        list.add(new News(R.drawable.chino,"la","la2"));
        list.add(new News(R.drawable.chino,"la","la2"));
        list.add(new News(R.drawable.chino,"la","la2"));
        list.add(new News(R.drawable.chino,"la","la2"));
    }
}