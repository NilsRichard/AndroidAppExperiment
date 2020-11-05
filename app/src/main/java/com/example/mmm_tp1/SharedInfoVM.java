package com.example.mmm_tp1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedInfoVM extends ViewModel {
    private final MutableLiveData<UserInfo> data = new MutableLiveData<UserInfo>();

    public void setdata(UserInfo newdata) {
        data.setValue(newdata);
    }

    public LiveData<UserInfo> getData() {
        return data;
    }
}
