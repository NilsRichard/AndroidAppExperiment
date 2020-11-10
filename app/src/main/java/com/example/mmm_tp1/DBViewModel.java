package com.example.mmm_tp1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DBViewModel extends AndroidViewModel {
    private final Repository repository;
    private final LiveData<List<UserInfo>> allUsers;

    public DBViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allUsers = repository.getAllUserInfos();
    }

    public void insert(UserInfo user) {
        repository.insert(user);
    }

    public void delete(UserInfo user) {
        repository.delete(user);
    }

    public LiveData<List<UserInfo>> getAllUsers() {
        return allUsers;
    }
}