package com.example.game_project;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;

public class Music extends AppCompatActivity {


    public static void pause(MediaPlayer mp) {

        if (mp != null) {
            if (mp.isPlaying()) {
                mp.stop();
            }
        }

    }
}

