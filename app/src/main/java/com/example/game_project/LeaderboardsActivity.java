package com.example.game_project;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

public class LeaderboardsActivity extends AppCompatActivity implements ClearDialog.ClearDialogListener{
    SQLiteDatabase theDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboards);

        Button clearButton = findViewById(R.id.clearData);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmClear(v);
            }
        });

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
                //ContentValues values = new ContentValues();

                //long newRowID = theDB.insert("leaderboard", null, values);
                TextView[] scoreText = new TextView[5];
                scoreText[0] = findViewById(R.id.score1Text);
                scoreText[1] = findViewById(R.id.score2Text);
                scoreText[2] = findViewById(R.id.score3Text);
                scoreText[3] = findViewById(R.id.score4Text);
                scoreText[4] = findViewById(R.id.score5Text);
                String[] columns = {"score"};

                Cursor c = theDB.query("leaderboard", columns,null, null,null,null,"score desc");
              if (c.moveToFirst()) {
                  int x = 0;
                  scoreText[x].setText(c.getString(c.getColumnIndex("score")));
                  while(c.moveToNext() && x < 4) {
                      x++;
                      scoreText[x].setText(c.getString(c.getColumnIndex("score")));
                  }
              }
              c.close();
              db.close();
            }
        });

    }
    public void confirmClear(View view){
        DialogFragment clearFragment = new ClearDialog();
        clearFragment.show(getFragmentManager(), "clearDialog");
    }

    public void onPositiveClick(){
        LeaderboardDB.getInstance(this).getWritableDatabase(new LeaderboardDB.OnDBReadyListener() {
            @Override
            public void onDBReady(SQLiteDatabase db) {
                theDB = db;
                db.execSQL("DELETE FROM leaderboard");
                TextView[] scoreText = new TextView[5];
                scoreText[0] = findViewById(R.id.score1Text);
                scoreText[1] = findViewById(R.id.score2Text);
                scoreText[2] = findViewById(R.id.score3Text);
                scoreText[3] = findViewById(R.id.score4Text);
                scoreText[4] = findViewById(R.id.score5Text);
                scoreText[0].setText("-----");
                scoreText[1].setText("-----");
                scoreText[2].setText("-----");
                scoreText[3].setText("-----");
                scoreText[4].setText("-----");
                db.close();
            }
        });

    }
}
