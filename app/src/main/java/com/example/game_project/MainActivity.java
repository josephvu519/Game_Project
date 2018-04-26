package com.example.game_project;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements QuitDialog.QuitDialogListener{

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences;
        sharedPreferences = getApplicationContext().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor settingsEditor = sharedPreferences.edit();
        settingsEditor.putBoolean("paused", false);
        settingsEditor.apply();

        final MediaPlayer mp = MediaPlayer.create(getApplicationContext(),R.raw.menu);
        mp.setVolume(sharedPreferences.getInt("sfxVolume",100),sharedPreferences.getInt("sfxVolume",100));
        mp.setLooping(true);
        mp.start();

        ImageView background = findViewById(R.id.background);
        background.setScaleType(ImageView.ScaleType.FIT_XY);

        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                mp.reset();
                mp.release();
                Intent intent = new Intent(getApplicationContext(), GameplayActivity.class);
                finish();
                startActivity(intent);
            }
        });

        Button leaderboardsButton = findViewById(R.id.leaderboardsButton);
        leaderboardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LeaderboardsActivity.class);
                startActivity(intent);
            }
        });

        Button settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        Button quitButton = findViewById(R.id.quitButton);
        //Callback code is invoked here
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmQuit(v);
            }
        });


    }
    public void confirmQuit(View view){
        DialogFragment quitFragment = new QuitDialog();
        quitFragment.show(getFragmentManager(), "quitDialog");
    }

    //Callback code is defined here
    public void onPositiveClick(){
        System.exit(0);
    }
}
