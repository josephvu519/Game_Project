package com.example.game_project;

/**
 * Created by adina on 3/19/2018.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

    public class LeaderboardDB extends SQLiteOpenHelper {

        interface OnDBReadyListener {
            void onDBReady(SQLiteDatabase theDB);
        }

        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "leaderboard.db";

        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE leaderboard (" +
                        "rank INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT, " +
                        "time TEXT)";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS leaderboard";

        private static LeaderboardDB theDb;
        private Context appContext;

        private LeaderboardDB(Context context) {
            super(context.getApplicationContext(),DATABASE_NAME,null,DATABASE_VERSION);
        }

        public static synchronized LeaderboardDB getInstance(Context context) {
            if (theDb == null) {
                theDb = new LeaderboardDB(context);
            }
            return theDb;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

        public void getWritableDatabase(OnDBReadyListener listener) {
            new OpenDbAsyncTask().execute(listener);
        }

        private static class OpenDbAsyncTask extends AsyncTask<OnDBReadyListener,Void,SQLiteDatabase> {
            OnDBReadyListener listener;

            @Override
            protected SQLiteDatabase doInBackground(OnDBReadyListener... params){
                listener = params[0];
                return LeaderboardDB.theDb.getWritableDatabase();
            }

            @Override
            protected void onPostExecute(SQLiteDatabase db) {
                listener.onDBReady(db);
            }
        }


    }