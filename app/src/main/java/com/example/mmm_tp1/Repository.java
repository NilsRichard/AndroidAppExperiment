package com.example.mmm_tp1;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    private final UserInfoDAO userDao;
    private final LiveData<List<UserInfo>> allUserInfos;

    public Repository(Application application) {
        AppDataBase database = AppDataBase.getInstance(application);
        userDao = database.userDao();
        allUserInfos = userDao.getAll();
    }

    public void insert(UserInfo note) {
        new InsertUserInfoAsyncTask(userDao).execute(note);
    }


    public void delete(UserInfo note) {
        new DeleteUserInfoAsyncTask(userDao).execute(note);
    }

    public void deleteAllUserInfos() {
        new DeleteAllUserInfosAsyncTask(userDao).execute();
    }

    public LiveData<List<UserInfo>> getAllUserInfos() {
        return allUserInfos;
    }

    private static class InsertUserInfoAsyncTask extends AsyncTask<UserInfo, Void, Void> {
        private UserInfoDAO userDao;

        private InsertUserInfoAsyncTask(UserInfoDAO userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserInfo... users) {
            userDao.insert(users[0]);
            return null;
        }
    }


    private static class DeleteUserInfoAsyncTask extends AsyncTask<UserInfo, Void, Void> {
        private UserInfoDAO userDao;

        private DeleteUserInfoAsyncTask(UserInfoDAO userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserInfo... users) {
            userDao.delete(users[0]);
            return null;
        }
    }

    private static class DeleteAllUserInfosAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserInfoDAO noteDao;

        private DeleteAllUserInfosAsyncTask(UserInfoDAO noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAll();
            return null;
        }
    }
}