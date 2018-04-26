package com.example.game_project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GameOverActivity extends AppCompatActivity {

    SQLiteDatabase theDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);
        TextView scoreText = findViewById(R.id.scoreValue);
        final int score = getIntent().getIntExtra("SCORE", 0);
        scoreText.setText(Integer.toString(score));

        final MediaPlayer pop = MediaPlayer.create(getApplicationContext(), R.raw.pop);
        pop.setLooping(true); // Set looping
        pop.start();


        LeaderboardDB.getInstance(this).getWritableDatabase(new LeaderboardDB.OnDBReadyListener() {
            @Override
            public void onDBReady(SQLiteDatabase db) {
                theDB = db;
                ContentValues values = new ContentValues();
                values.put("score", score);
                long newRowID = theDB.insert("leaderboard", null, values);
                db.close();
            }
        });

        Button playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.stop();
                pop.reset();
                pop.release();
                Intent intent = new Intent(getApplicationContext(), GameplayActivity.class);
                finish();
                startActivity(intent);
            }
        });

        Button mainMenuButton = findViewById(R.id.mainMenuButton);
        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.stop();
                pop.reset();
                pop.release();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

        Button leaderboardsButton = findViewById(R.id.leaderboardsButton);
        leaderboardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LeaderboardsActivity.class);
                startActivity(intent);
            }
        });
    }
}
