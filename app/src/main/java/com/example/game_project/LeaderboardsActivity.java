package com.example.game_project;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

public class LeaderboardsActivity extends AppCompatActivity {
    SQLiteDatabase theDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboards);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Get a writable database
        LeaderboardDB.getInstance(this).getWritableDatabase(new LeaderboardDB.OnDBReadyListener() {
            @Override
            public void onDBReady(SQLiteDatabase db) {
                theDB = db;
                ContentValues values = new ContentValues();
                values.put("rank", "1");
                values.put("name", "bruh");
                values.put("time", "15:30");

                long newRowID = theDB.insert("leaderboard", null, values);
                TextView time1text = findViewById(R.id.time1Text);
                String[] columns = {"rank","name","time"};
                Cursor c = theDB.query("leaderboard", columns,"rank = 1", null,null,null,null);
              if (c.moveToFirst()) {
                  time1text.setText(c.getString(c.getColumnIndex("time")));
              }
              c.close();
              db.execSQL("DELETE FROM leaderboard WHERE rank = '1'");
              db.close();
            }
        });

    }
}
