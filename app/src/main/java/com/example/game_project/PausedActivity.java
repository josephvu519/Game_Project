package com.example.game_project;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PausedActivity extends AppCompatActivity implements QuitDialog.QuitDialogListener{

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paused);

        Context context = getApplicationContext();
        sharedPreferences = context.getSharedPreferences("gamePaused", Context.MODE_PRIVATE);
        final SharedPreferences.Editor settingsEditor = sharedPreferences.edit();
        settingsEditor.putBoolean("paused", true);
        settingsEditor.apply();

        Button resumeButton = findViewById(R.id.resumeButton);
        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsEditor.putBoolean("paused", false);
                settingsEditor.apply();
                finish();
            }
        });

        Button pauseButton = findViewById(R.id.quitButton);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmQuit(v);
            }
        });

        Button mainMenuButton = findViewById(R.id.mainMenuButton);
        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    public void confirmQuit(View view){
        DialogFragment quitFragment = new QuitDialog();
        quitFragment.show(getFragmentManager(), "quitDialog");
    }

    public void onPositiveClick(){
        finishAffinity();
    }
}
