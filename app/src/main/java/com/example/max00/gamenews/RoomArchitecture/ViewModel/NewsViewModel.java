package com.example.max00.gamenews.RoomArchitecture.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.max00.gamenews.RoomArchitecture.Entity.NewsEntity;
import com.example.max00.gamenews.RoomArchitecture.Repository.NewsRepository;

import java.util.List;

public class NewsViewModel extends AndroidViewModel{

    private NewsRepository newsRepository;
    private LiveData<List<NewsEntity>> mAllNews;
    private LiveData<List<NewsEntity>> categorizednews;
    private LiveData<List<NewsEntity>> categorizedoverwatch;
    private LiveData<List<NewsEntity>> categorizedcsgo;

    public NewsViewModel(Application application){
        super(application);
        newsRepository = new NewsRepository(application);
        mAllNews = newsRepository.getmAllNews();
        categorizednews = newsRepository.getCategorizednews();
        categorizedoverwatch = newsRepository.getCategorizedoverwatch();
        categorizedcsgo = newsRepository.getCategorizedcsgo();
    }

    public LiveData<List<NewsEntity>> getmAllNews(){
        return mAllNews;
    }

    public LiveData<List<NewsEntity>> getCategorizednews(){
        return categorizednews;
    }

    public LiveData<List<NewsEntity>> getCategorizedoverwatch() {
        return categorizedoverwatch;
    }

    public LiveData<List<NewsEntity>> getCategorizedcsgo() {
        return categorizedcsgo;
    }

    public void insert(List<NewsEntity> newsEntity){
        newsRepository.insert(newsEntity);
    }
}
