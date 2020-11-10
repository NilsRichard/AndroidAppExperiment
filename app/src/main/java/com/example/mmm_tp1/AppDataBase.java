package com.example.mmm_tp1;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {UserInfo.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance;

    public abstract UserInfoDAO userDao();

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserInfoDAO noteDao;

        private PopulateDbAsyncTask(AppDataBase db) {
            noteDao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            UserInfo u1 = new UserInfo("Ada","Lovelace","nantes","20/02/1998","0518947856");
            UserInfo u2 = new UserInfo("Charles","Babbage","rennes","15/10/1985","0564987584");

            noteDao.insert(u1);
            noteDao.insert(u2);
            noteDao.insert(u1);
            noteDao.insert(u2);

            return null;
        }
    }

}
