package com.example.game_project.Accessories;


import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.game_project.GameViewActivity;
import com.example.game_project.GameplayActivity;
import com.example.game_project.R;
import com.example.game_project.Util;

/**
 * Created by adina on 3/17/2018.
 */

public class Background extends Character{
    /** Static bitmap to reduce memory usage */
    public static Bitmap globalBitmap;

    public Background(GameViewActivity view, GameplayActivity game) {
        super(view, game);

        if(globalBitmap == null){
            globalBitmap = Util.getDownScaledBitmapAlpha8(game, R.drawable.background);
        }
        this.bitmap = globalBitmap;
    }

    @Override
    public void draw(Canvas canvas) {
        double factor = (1.0 * canvas.getHeight()) / bitmap.getHeight();

        if(-x > bitmap.getWidth()){
            // The first bitmap is completely out of the screen
            x += bitmap.getWidth();
        }

        int endBitmap = Math.min(-x + (int) (canvas.getWidth() / factor), bitmap.getWidth());
        int endCanvas = (int) ((endBitmap + x) * factor) + 1;
        src.set(-x, 0, endBitmap, bitmap.getHeight());
        dst.set(0, 0, endCanvas, canvas.getHeight());
        canvas.drawBitmap(this.bitmap, src, dst, null);

        if(endBitmap == bitmap.getWidth()){
            // draw second bitmap
            src.set(0, 0, (int) (canvas.getWidth() / factor), bitmap.getHeight());
            dst.set(endCanvas, 0, endCanvas + canvas.getWidth(), canvas.getHeight());
            canvas.drawBitmap(this.bitmap, src, dst, null);
        }
    }
}

