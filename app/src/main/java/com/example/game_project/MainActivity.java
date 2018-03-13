package com.example.game_project;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements QuitDialog.QuitDialogListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button quitButton = findViewById(R.id.quitButton);
        //Callback code is invoked here
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmQuit(v);
            }
        });

        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameplayActivity.class);
                finish();
                startActivity(intent);
            }
        });

    }
    public void confirmQuit(View view){
        DialogFragment quitFragment = new QuitDialog();
        quitFragment.show(getFragmentManager(), "quitDialog");
    }

    //Callback code is defined here
    public void onPositiveClick(){
        System.exit(0);
    }
}
