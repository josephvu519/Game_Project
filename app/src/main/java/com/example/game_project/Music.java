package com.example.game_project;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;

public class Music extends AppCompatActivity {

    public static void SoundPlayer(Context ctx, int id){

        MediaPlayer player1 = MediaPlayer.create(ctx, R.raw.normal);
        MediaPlayer player0 = MediaPlayer.create(ctx, R.raw.menu);

        if (id == 1)
        {
            player1.setLooping(true); // Set looping
            player1.start();
        }
        else if(id == 11)
        {
            player1.reset();
            player1.release();
        }
       else if(id == 2)
        {
            player0.setLooping(false); // Set looping
            player0.start();
        }
        else if(id == 10)
        {
            player0.stop();
            player0.reset();
            player0.release();
        }
       else if (id == 2)
        {
            //medium
        }
       else if(id == 3)
        {
            //crazy
        }



    }
}
