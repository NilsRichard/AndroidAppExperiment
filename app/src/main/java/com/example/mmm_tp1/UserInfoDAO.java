package com.example.mmm_tp1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserInfoDAO {

    @Query("SELECT * FROM user_info ")
    LiveData<List<UserInfo>> getAll();

    @Query("SELECT * FROM user_info WHERE uid IN (:userIds)")
    LiveData<List<UserInfo>> loadAllByIds(int[] userIds);


    @Query("SELECT * FROM user_info WHERE nom LIKE :first AND " +
            "prenom LIKE :last LIMIT 1")
    UserInfo findByName(String first, String last);

    @Insert
    void insertAll(UserInfo... users);

    @Insert
    void insert(UserInfo user);

    @Delete
    void delete(UserInfo user);

    @Delete
    void deleteAll(UserInfo... users);

}
