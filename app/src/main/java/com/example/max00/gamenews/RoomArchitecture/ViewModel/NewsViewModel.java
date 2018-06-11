package com.example.max00.gamenews.RoomArchitecture.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.max00.gamenews.Adapters.NewsAdapter;
import com.example.max00.gamenews.RoomArchitecture.Entity.NewsEntity;
import com.example.max00.gamenews.RoomArchitecture.Repository.NewsRepository;

import java.util.List;

public class NewsViewModel extends AndroidViewModel{

    private NewsRepository newsRepository;
    private LiveData<List<NewsEntity>> mAllNews;

    public NewsViewModel(Application application){
        super(application);
        newsRepository = new NewsRepository(application);
        mAllNews = newsRepository.getmAllNews();
    }

    public LiveData<List<NewsEntity>> getmAllNews(){
        return mAllNews;
    }

    public void insert(List<NewsEntity> newsEntity){
        newsRepository.insert(newsEntity);
    }
}
