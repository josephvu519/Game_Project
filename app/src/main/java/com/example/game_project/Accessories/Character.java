package com.example.game_project.Accessories;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.game_project.GameplayActivity;

/**
 * Created by adina on 3/17/2018.
 */

public abstract class Character {
    /** The bitmaps that holds the frames that should be drawn */
    protected Bitmap bitmap;

    /** Height and width of one frame of the bitmap */
    protected int height, width;

    /** x and y coordinates on the canvas */
    protected int x, y;

    /** Horizontal and vertical speed of the sprite */
    protected float speedX, speedY;

    /** The source frame of the bitmap that should be drawn */
    protected Rect src;

    /** The destination area that the frame should be drawn to */
    protected Rect dst;

    /** Coordinates of the frame in the spritesheet */
    protected byte col, row;

    /** Number of columns the sprite has */
    protected byte colNr = 1;

    /** How long a frame should be displayed */
    protected short frameTime;

    /**
     * Counter for the frames
     * Cycling through the columns
     */
    protected short frameTimeCounter;

    /** The GameView that holds this Sprite */
    protected GameplayActivity view;


}

