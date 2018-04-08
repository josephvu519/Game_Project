package com.example.game_project;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PausedActivity extends AppCompatActivity implements QuitDialog.QuitDialogListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paused);

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
