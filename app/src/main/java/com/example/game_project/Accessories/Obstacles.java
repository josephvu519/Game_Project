package com.example.game_project.Accessories;

/**
 * Created by arl5500 on 4/19/18.
 */



import android.graphics.Canvas;

import com.example.game_project.GameViewActivity;
import com.example.game_project.GameplayActivity;
import com.example.game_project.R;


    public class Obstacles extends Character {

        private Spike spike;

        /**
         * Necessary so the onPass method is just called once
         */
        public boolean isAlreadyPassed = false;

        public Obstacles(GameViewActivity view, GameplayActivity game) {
            super(view, game);
            spike = new Spike(view, game);


            initPos();
        }

        /**
         * Creates a spike and a wooden spike at the right of the screen.
         * With a certain gap between them.
         * The vertical position is in a certain area random.
         */
        private void initPos() {
            int height = game.getResources().getDisplayMetrics().heightPixels;
            int gab = height / 4 - view.getSpeedX();
            if (gab < height / 5) {
                gab = height / 5;
            }
            int random = (int) (Math.random() * height * 2 / 5);
            int y1 = (height / 10) + random - spike.height;
            int y2 = (height / 10) + random + gab;

            spike.init(game.getResources().getDisplayMetrics().widthPixels, y1);
            spike.init(game.getResources().getDisplayMetrics().widthPixels, y2);
        }

        /**
         * Draws spike and spike.
         */
        @Override
        public void draw(Canvas canvas) {
            spike.draw(canvas);
            spike.draw(canvas);
        }

        /**
         * Checks whether both, spike and spike, are out of range.
         */
        @Override
        public boolean isOutOfRange() {
            return spike.isOutOfRange() && spike.isOutOfRange();
        }

        /**
         * Checks whether the spike or the spike is colliding with the sprite.
         */
        @Override
        public boolean isColliding(Character sprite) {
            return spike.isColliding(sprite) || spike.isColliding(sprite);
        }

        /**
         * Moves both, spike and spike.
         */
        @Override
        public void move() {
            spike.move();
            spike.move();
        }

        /**
         * Sets the speed of the spike and the spike.
         */
        @Override
        public void setSpeedX(float speedX) {
            spike.setSpeedX(speedX);
            spike.setSpeedX(speedX);
        }

        /**
         * Checks whether the spike and the spike are passed.
         *//*
        @Override
        public boolean isPassed() {
            return spike.isPassed() && spike.isPassed();
        }*/

      //  private static final int SOUND_VOLUME_DIVIDER = 3;

        /**
         * Will call obstaclePassed of the game, if this is the first pass of this obstacle.
         */
        public void onPass() {
            if (!isAlreadyPassed) {
                isAlreadyPassed = true;
             //   view.getGame().increasePoints();
               // GameplayActivity.soundPool.play(passSound, MainActivity.volume / SOUND_VOLUME_DIVIDER, MainActivity.volume / SOUND_VOLUME_DIVIDER, 0, 0, 1);
            }
        }

        @Override
        public void onCollision() {
            super.onCollision();
          //  Game.soundPool.play(collideSound, MainActivity.volume / SOUND_VOLUME_DIVIDER, MainActivity.volume / SOUND_VOLUME_DIVIDER, 0, 0, 1);
        }

    }

