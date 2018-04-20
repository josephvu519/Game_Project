package com.example.game_project.Accessories;

import android.graphics.Bitmap;

import com.example.game_project.GameViewActivity;
import com.example.game_project.GameplayActivity;
import com.example.game_project.R;
import com.example.game_project.Util;

/**
 * Created by adina on 3/17/2018.
 */

public class Spike extends Character {

    /**
     * Static bitmap to reduce memory usage.
     */
    public static Bitmap globalBitmap;

    public Spike(GameViewActivity view, GameplayActivity game) {
        super(view, game);
        if(globalBitmap == null){
            globalBitmap = Util.getScaledBitmapAlpha8(game, R.drawable.spike);
        }
        this.bitmap = globalBitmap;
        this.width = this.bitmap.getWidth();
        this.height = this.bitmap.getHeight();
    }

    /**
     * Sets the position
     * @param x
     * @param y
     */
    public void init(int x, int y){
        this.x = x;
        this.y = y;
    }
}