package com.example.game_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.game_project.Accessories.Timer;


public class GameplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameplay);
        final Timer timer = new Timer();

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

        Button kysButton = findViewById(R.id.kysButton);
        kysButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameOverActivity.class);
                startActivity(intent);

                timer.getElapsedTimeString();
            }
        });



    }
}
