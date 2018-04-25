package com.example.game_project;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.os.Handler;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.util.Random;

public class GameplayActivity extends AppCompatActivity{

    //GameViewActivity view;
    final int spikeGap = 350;
    final int spikePixelSpeed = 10;
    Random generator = new Random();
    final int maxSpikePercent = 75;
    boolean collided = false;
    int screenHeight;
    int screenWidth;
    boolean gameEnded = false;
    boolean pointCheck = false;
    int score = 0;
    boolean pause = false;

    TextView timer;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameplay);
        timer = (TextView)findViewById(R.id.timer);
        //TEST
        final ImageView bubble = findViewById(R.id.bubble);
        final ImageView spikeLeft1 = findViewById(R.id.spikeLeft1);
        final ImageView spikeLeft2 = findViewById(R.id.spikeLeft2);
        final ImageView spikeLeft3 = findViewById(R.id.spikeLeft3);
        final ImageView spikeRight1 = findViewById(R.id.spikeRight1);
        final ImageView spikeRight2 = findViewById(R.id.spikeRight2);
        final ImageView spikeRight3 = findViewById(R.id.spikeRight3);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels*2;
      //  background.setMinimumHeight(screenHeight);
        screenWidth = displayMetrics.widthPixels;
        final int maxTilt = 10;
     //   final TextView axisX = findViewById(R.id.xAxis);
        //-------
//--------------------------------------------------------------------------------------------------
        int initialXY = generator.nextInt(maxSpikePercent);
        ConstraintLayout.LayoutParams ls1 = (ConstraintLayout.LayoutParams) spikeLeft1.getLayoutParams();
        ConstraintLayout.LayoutParams ls2 = (ConstraintLayout.LayoutParams) spikeLeft2.getLayoutParams();
        ConstraintLayout.LayoutParams ls3 = (ConstraintLayout.LayoutParams) spikeLeft3.getLayoutParams();
        ConstraintLayout.LayoutParams rs1 = (ConstraintLayout.LayoutParams) spikeRight1.getLayoutParams();
        ConstraintLayout.LayoutParams rs2 = (ConstraintLayout.LayoutParams) spikeRight2.getLayoutParams();
        ConstraintLayout.LayoutParams rs3 = (ConstraintLayout.LayoutParams) spikeRight3.getLayoutParams();

        ls1.setMargins(0, 0, screenWidth-(int)(initialXY/100.0*screenWidth), (int)(screenHeight));
        rs1.setMargins(screenWidth-ls1.rightMargin + spikeGap, 0, 0, (screenHeight));
        initialXY = generator.nextInt(maxSpikePercent);
        spikeLeft1.setLayoutParams(ls1);
        spikeRight1.setLayoutParams(rs1);


        ls2.setMargins(0, 0, screenWidth-(int)(initialXY/100.0*screenWidth), (int)(screenHeight*1.5));
        rs2.setMargins(screenWidth-ls2.rightMargin + spikeGap, 0, 0, (int)(screenHeight*1.5));
        initialXY = generator.nextInt(maxSpikePercent);
        spikeLeft2.setLayoutParams(ls2);
        spikeRight2.setLayoutParams(rs2);

        ls3.setMargins(0, 0, screenWidth-(int)(initialXY/100.0*screenWidth), (int)(screenHeight*2));
        rs3.setMargins(screenWidth - ls3.rightMargin + spikeGap, 0, 0, (int)(screenHeight*2));
        spikeLeft3.setLayoutParams(ls3);
        spikeRight3.setLayoutParams(rs3);
        //--------------------------------------------------------------------------------------------------

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

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
        SensorEventListener gyroscopeEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (collided == false) {
                    double axisY = sensorEvent.values[1] * 180 / Math.PI;
                    double axisZ = sensorEvent.values[2] * 180 / Math.PI;
                    ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) bubble.getLayoutParams();
                    ConstraintLayout.LayoutParams ls1 = (ConstraintLayout.LayoutParams) spikeLeft1.getLayoutParams();
                    ConstraintLayout.LayoutParams ls2 = (ConstraintLayout.LayoutParams) spikeLeft2.getLayoutParams();
                    ConstraintLayout.LayoutParams ls3 = (ConstraintLayout.LayoutParams) spikeLeft3.getLayoutParams();
                    ConstraintLayout.LayoutParams rs1 = (ConstraintLayout.LayoutParams) spikeRight1.getLayoutParams();
                    ConstraintLayout.LayoutParams rs2 = (ConstraintLayout.LayoutParams) spikeRight2.getLayoutParams();
                    ConstraintLayout.LayoutParams rs3 = (ConstraintLayout.LayoutParams) spikeRight3.getLayoutParams();
                    shiftSpike(ls1, spikeLeft1, rs1, spikeRight1, screenHeight, screenWidth);
                    shiftSpike(ls2, spikeLeft2, rs2, spikeRight2, screenHeight, screenWidth);
                    shiftSpike(ls3, spikeLeft3, rs3, spikeRight3, screenHeight, screenWidth);
                    collided = checkCollision(bubble,spikeLeft1, collided);
                    collided = checkCollision(bubble,spikeLeft2, collided);
                    collided = checkCollision(bubble,spikeLeft3, collided);
                    pointAdd(bubble, spikeLeft1, collided);
                    pointAdd(bubble, spikeLeft2, collided);
                    pointAdd(bubble, spikeLeft3, collided);


                    if (axisY > maxTilt) {
                        lp.setMargins((screenWidth - bubble.getWidth()), 0, 0, (int) (screenHeight / 2 * .3));
                        bubble.setLayoutParams(lp);
                    } else if (axisY < -maxTilt) {
                        lp.setMargins(0, 0, 0, (int) (screenHeight / 2 * .3));
                        bubble.setLayoutParams(lp);
                    } else {
                        lp.setMargins((int) ((screenWidth / 2 - bubble.getWidth() / 2) + axisY * (screenWidth - bubble.getWidth()) / 2 / maxTilt), 0, 0, (int) (screenHeight / 2 * .3));
                        bubble.setLayoutParams(lp);
                        //axisX.setText("Z Axis: " + sensorEvent.values[2] * 180 /Math.PI);
                    }
                    timer.setText(Integer.toString(score) + "M");
                }
               else if(gameEnded == false){
                    gameEnded = true;
                    Bundle instanceState = new Bundle();
                    instanceState.putString("time", "32:09");
                    onStop(instanceState);

                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sensorManager.registerListener(gyroscopeEventListener, rotationSensor, SensorManager.SENSOR_DELAY_FASTEST);

    }

    protected void onPause(Bundle savedInstanceState){
        Intent intent = new Intent(getApplicationContext(), PausedActivity.class);
        startActivity(intent);
    }

    protected void onStop(Bundle savedInstanceState){


        Intent intent = new Intent(getApplicationContext(), GameOverActivity.class);
        intent.putExtra("SCORE", score);
        startActivity(intent);


    }

    protected void onResume(Bundle savedInstanceSteate){

    }

    private void shiftSpike(ConstraintLayout.LayoutParams positionLayoutLeft, ImageView spikeLeft, ConstraintLayout.LayoutParams positionLayoutRight, ImageView spikeRight, int screenHeight, int screenWidth){
        if (positionLayoutLeft.topMargin > spikeLeft.getHeight()){
            int newXY = generator.nextInt(maxSpikePercent);

            positionLayoutLeft.setMargins(0, 0, screenWidth-(int)(newXY*screenWidth/100.0), (int)(screenHeight*1.5));
            positionLayoutRight.setMargins(screenWidth-positionLayoutLeft.rightMargin + spikeGap, 0, 0, (int)(screenHeight*1.5));
        }
        else{
            if (positionLayoutLeft.bottomMargin == 0){
                positionLayoutLeft.setMargins(0, positionLayoutLeft.topMargin+spikePixelSpeed, positionLayoutLeft.rightMargin, 0);
                positionLayoutRight.setMargins(positionLayoutRight.leftMargin, positionLayoutLeft.topMargin+spikePixelSpeed, 0, 0);
            }
            else if (positionLayoutLeft.bottomMargin-spikePixelSpeed <= 0){
                int difference = spikePixelSpeed - positionLayoutLeft.bottomMargin;
                positionLayoutLeft.setMargins(0, difference, positionLayoutLeft.rightMargin, 0);
                positionLayoutRight.setMargins(positionLayoutRight.leftMargin, difference, 0, 0);
            }
            else {
                positionLayoutLeft.setMargins(0, 0, positionLayoutLeft.rightMargin, positionLayoutLeft.bottomMargin - spikePixelSpeed);
                positionLayoutRight.setMargins(positionLayoutRight.leftMargin, 0, 0, positionLayoutLeft.bottomMargin - spikePixelSpeed);
            }
        }
        spikeLeft.setLayoutParams(positionLayoutLeft);
        spikeRight.setLayoutParams(positionLayoutRight);
    }

    public Boolean checkCollision(ImageView bubble, ImageView spikeLeft, boolean collided){
        if (collided == true)
            return true;
        ConstraintLayout.LayoutParams bubblePosition = (ConstraintLayout.LayoutParams) bubble.getLayoutParams();
        ConstraintLayout.LayoutParams spikeLeftPosition = (ConstraintLayout.LayoutParams) spikeLeft.getLayoutParams();
        if (bubblePosition.bottomMargin >= spikeLeftPosition.bottomMargin - spikeLeft.getHeight()*2 && bubblePosition.bottomMargin <= spikeLeftPosition.bottomMargin + bubble.getHeight()*2){
            if (bubblePosition.leftMargin <= screenWidth-spikeLeftPosition.rightMargin || bubblePosition.leftMargin >= screenWidth-spikeLeftPosition.rightMargin + spikeGap)
            return true;
        }
        return false;


    }
    public void pointAdd (ImageView bubble, ImageView spikeLeft, boolean collided){
        ConstraintLayout.LayoutParams bubblePosition = (ConstraintLayout.LayoutParams) bubble.getLayoutParams();
        ConstraintLayout.LayoutParams spikeLeftPosition = (ConstraintLayout.LayoutParams) spikeLeft.getLayoutParams();
        if (bubblePosition.bottomMargin >= spikeLeftPosition.bottomMargin - spikeLeft.getHeight()*2 && bubblePosition.bottomMargin <= spikeLeftPosition.bottomMargin + bubble.getHeight()*2){
            pointCheck = true;
        }
        if(pointCheck){

            if(bubblePosition.bottomMargin >= spikeLeftPosition.bottomMargin + bubble.getHeight()*2){
                pointCheck = false;

                timer.setText(Integer.toString(score += 10));
                //score

                timer.setText(Integer.toString(score += 10) + "M");


            }
        }
    }


}
