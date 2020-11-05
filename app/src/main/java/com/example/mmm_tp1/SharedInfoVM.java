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

    public void setUserInfos(List<UserInfo> newList) {
        userInfos.setValue(newList);
    }
    public LiveData<List<UserInfo>> getUserInfos() {
        return userInfos;
    }
}
