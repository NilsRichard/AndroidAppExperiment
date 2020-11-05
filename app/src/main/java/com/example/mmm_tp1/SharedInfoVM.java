package com.example.mmm_tp1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SharedInfoVM extends ViewModel {
    private final MutableLiveData<UserInfo> currentUserInfo = new MutableLiveData<>();

    private final MutableLiveData<List<UserInfo>> userInfos = new MutableLiveData<>();

    public void setCurrentUserInfo(UserInfo newdata) {
        currentUserInfo.setValue(newdata);
    }
    public LiveData<UserInfo> getCurrentUserInfo() {
        return currentUserInfo;
    }

    public void addUserInfo(UserInfo userInfo) {
        if(userInfos.getValue() == null) userInfos.setValue(new ArrayList<>());
        userInfos.getValue().add(userInfo);
    }

    public LiveData<List<UserInfo>> getUserInfos() {
        return userInfos;
    }
}
