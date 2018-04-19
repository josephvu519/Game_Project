package com.example.game_project;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.game_project.Accessories.Timer;
import com.q42.android.scrollingimageview.ScrollingImageView;

public class GameplayActivity extends AppCompatActivity {


   // GameViewActivity view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameplay);
        final Timer timer = new Timer();

       // view = new GameViewActivity(this);
      //  setContentView(view);
       // ScrollingImageView background = findViewById(R.id.scrolling_background);

        //TEST
        final ImageView bubble = findViewById(R.id.bubble);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int screenHeight = displayMetrics.heightPixels;
      //  background.setMinimumHeight(screenHeight);
        final int screenWidth = displayMetrics.widthPixels;
        final int maxTilt = 10;
     //   final TextView axisX = findViewById(R.id.xAxis);
        //-------


        Toast instruction = Toast.makeText(this, "Tilt the screen to move", Toast.LENGTH_LONG);
        instruction.setGravity(Gravity.TOP, 0, 0);
        instruction.show();

        Button pauseButton = findViewById(R.id.pauseButton);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PausedActivity.class);
                startActivity(intent);
            }
        });

      //  Button kysButton = findViewById(R.id.kysButton);
      /*  kysButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameOverActivity.class);
                startActivity(intent);

                timer.getElapsedTimeString();
            }
        });*/


        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
        SensorEventListener gyroscopeEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                double axisY = sensorEvent.values[1] * 180 / Math.PI;
                double axisZ = sensorEvent.values[2] * 180 / Math.PI;
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) bubble.getLayoutParams();
                if (axisY > maxTilt){
                    lp.setMargins((screenWidth - bubble.getWidth()), (int)(screenHeight*.8), 0, 0);
                    bubble.setLayoutParams(lp);
                }
                else if (axisY < -maxTilt) {
                    lp.setMargins(0, (int)(screenHeight*.8), (screenWidth/2), 80);
                    bubble.setLayoutParams(lp);
                }
                else {
                    lp.setMargins((int)((screenWidth/2 - bubble.getWidth()/2) + axisY * (screenWidth-bubble.getWidth())/2/maxTilt), (int)(screenHeight*.8), 0, 80);
                    bubble.setLayoutParams(lp);
                    //axisX.setText("Z Axis: " + sensorEvent.values[2] * 180 /Math.PI);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sensorManager.registerListener(gyroscopeEventListener, rotationSensor, SensorManager.SENSOR_DELAY_FASTEST);

    }
}
