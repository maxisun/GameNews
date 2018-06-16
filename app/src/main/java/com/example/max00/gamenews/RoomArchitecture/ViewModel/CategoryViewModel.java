package com.example.max00.gamenews.RoomArchitecture.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.max00.gamenews.RoomArchitecture.Entity.CategoryEntity;
import com.example.max00.gamenews.RoomArchitecture.Repository.CategoryRepository;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private CategoryRepository categoryRepository;
    private LiveData<List<CategoryEntity>> allCategories;

    public CategoryViewModel(Application application){
        super(application);
        categoryRepository = new CategoryRepository(application);
    }

    public LiveData<List<CategoryEntity>> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    public void insert(CategoryEntity categoryEntity){
        categoryRepository.insert(categoryEntity);
    }
}
