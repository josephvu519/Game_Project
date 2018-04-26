package com.example.game_project;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;

public class Music extends AppCompatActivity {

    public static MediaPlayer pop;

    public static void SoundPlayer(Context ctx, int id) {

        MediaPlayer pop = MediaPlayer.create(ctx, R.raw.normal);
        pop.setLooping(true); // Set looping
        pop.start();
    }

}

