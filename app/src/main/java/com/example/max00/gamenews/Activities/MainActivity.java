package com.example.max00.gamenews.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.max00.gamenews.RoomArchitecture.Entity.CategoryEntity;
import com.example.max00.gamenews.RoomArchitecture.Repository.NewsRepository.fetchNews;
import com.example.max00.gamenews.Fragments.CustomGameFragment;
import com.example.max00.gamenews.Fragments.NewsFragment;
import com.example.max00.gamenews.R;
import com.example.max00.gamenews.RoomArchitecture.ViewModel.CategoryViewModel;
import com.example.max00.gamenews.RoomArchitecture.ViewModel.NewsViewModel;
import com.example.max00.gamenews.RoomArchitecture.ViewModel.PlayersViewModel;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mactionBarDrawerToggle;
    private NewsViewModel newsViewModel;
    private PlayersViewModel playersViewModel;
    private CategoryViewModel categoryViewModel;
    private NavigationView navigationView;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //shared preference
        sharedpreferences();
        setContentView(R.layout.activity_main);
        //inicializando atributos y consiguiendo cosas del API
        initialize();
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
                        fragment = NewsFragment.newInstance(item.getTitle().toString());
                        fragtransac = true;
                        break;
                    case R.id.menu_settings:
                        Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        //lo que sea dinamico (al no tener un id estatico para referencia de titulo) se pasa al default del switch
                        fragment = CustomGameFragment.newInstance(item.getTitle().toString().toLowerCase());
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
        newsViewModel = new NewsViewModel(getApplication());
        playersViewModel = new PlayersViewModel(getApplication());
        fetchNews.setContext(getApplicationContext());
        categoryViewModel = new CategoryViewModel(getApplication());
        categoryViewModel.getAllCategories().observe(this,this::addMenuItems);
    }

    //funcion para hacer que funcione el boton para mostrar el drawerlayout
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mactionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /*private void setFragmentByDefault(){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, newsFragment).commit();
        MenuItem item = navigationView.getMenu().getItem(0);
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
    }*/

    private void sharedpreferences(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("Login_Token", Context.MODE_PRIVATE);
        if(!sharedPreferences.contains("Token")){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }else {
            token = sharedPreferences.getString("Token","");
        }
    }

    private void addMenuItems(List<CategoryEntity> categories) {
        //se limpia para evitar algun conflicto
        navigationView.getMenu().findItem(R.id.categories_games).getSubMenu().clear();
        //recorremos la lista que estamos observando para setetearle las categorias el submenu que tenemos
        for (CategoryEntity i : categories) {
            navigationView.getMenu().findItem(R.id.categories_games).getSubMenu().add(i.getCategory()).setCheckable(false).setIcon(R.drawable.icono_bookmark);
        }
    }

}
