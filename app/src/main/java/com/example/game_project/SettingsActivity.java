package com.example.game_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class SettingsActivity extends AppCompatActivity {

    private String SettingPREFERENCES = "SettingPrefs";
    private String MusicVolume = "musicVol";
    private String FXVolume = "fxVol";
    private boolean Hint = true;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        Context context = getApplicationContext();
        sharedPreferences = context.getSharedPreferences(getString(R.string.settings), Context.MODE_PRIVATE);
        final SharedPreferences.Editor settingsEditor = sharedPreferences.edit();

        //Music/BGM
        final SeekBar musicSeekBar = findViewById(R.id.bgmBar);
        int bgm = sharedPreferences.getInt("musicVolume", 100);
        musicSeekBar.setProgress(bgm);

        final CheckBox musicMute = findViewById(R.id.musicMuteCheckbox);
        boolean bgmMute = sharedPreferences.getBoolean("musicMute", false);
        musicMute.setChecked(bgmMute);




        //Sound FX
        final SeekBar sfxSeekBar = findViewById(R.id.sfxBar);
        int sfx = sharedPreferences.getInt("sfxVolume", 100);
        sfxSeekBar.setProgress(sfx);



        final CheckBox soundMute = findViewById(R.id.soundMuteCheckbox);
        boolean sfxMute = sharedPreferences.getBoolean("sfxMute", false);
        soundMute.setChecked(sfxMute);


        Button okButton = findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsEditor.putInt("musicVolume", musicSeekBar.getProgress());
                settingsEditor.putBoolean("musicMute", musicMute.isChecked());
                settingsEditor.putInt("sfxVolume", sfxSeekBar.getProgress());
                settingsEditor.putBoolean("sfxMute", soundMute.isChecked());
                settingsEditor.apply();
                onStop();
            }
        });

        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStop();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
