package com.example.game_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

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
        /*final SeekBar musicSeekBar = findViewById(R.id.bgmBar);
        int bgm = sharedPreferences.getInt("musicVolume", 100);
        musicSeekBar.setProgress(bgm);

        final CheckBox musicMute = findViewById(R.id.musicMuteCheckbox);
        boolean bgmMute = sharedPreferences.getBoolean("musicMute", false);
        musicMute.setChecked(bgmMute);*/


        final CheckBox tutorialEnabled = findViewById(R.id.tutorialBox);
        boolean tutorial = sharedPreferences.getBoolean("tutorial", true);
        tutorialEnabled.setChecked(tutorial);


        //Sound FX
        /*final SeekBar sfxSeekBar = findViewById(R.id.sfxBar);
        int sfx = sharedPreferences.getInt("sfxVolume", 100);
        sfxSeekBar.setProgress(sfx);*/

        final RatingBar difficultySetting = findViewById(R.id.difficultySetting);
        int difficultyLevel = sharedPreferences.getInt("difficulty", 1);
        difficultySetting.setRating((float)difficultyLevel);

        final TextView difficultyReading = findViewById(R.id.difficultyDisplay);
        final String[] difficulties = {"", "Normal", "Hard", "Crazy!"};
        difficultyReading.setText(difficulties[difficultyLevel]);

        difficultySetting.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                difficultyReading.setText(difficulties[(int)rating]);
            }
        });


/*

        final CheckBox soundMute = findViewById(R.id.soundMuteCheckbox);
        boolean sfxMute = sharedPreferences.getBoolean("sfxMute", false);
        soundMute.setChecked(sfxMute);
*/


        Button okButton = findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* settingsEditor.putInt("musicVolume", musicSeekBar.getProgress());
                settingsEditor.putBoolean("musicMute", musicMute.isChecked());
                settingsEditor.putInt("sfxVolume", sfxSeekBar.getProgress());
                settingsEditor.putBoolean("sfxMute", soundMute.isChecked());*/
                settingsEditor.putBoolean("tutorial", tutorialEnabled.isChecked());
                settingsEditor.putInt("difficulty", (int)difficultySetting.getRating());
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
