package com.example.game_project;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class QuitDialog extends DialogFragment{
    //Interface is defined here
    public interface QuitDialogListener{
        public void onPositiveClick();
    }
    //Object that implements the Interface
    QuitDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Quit")
                .setMessage("Are you sure you want to quit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onPositiveClick();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {}//Empty and does nothing
                });
        return builder.create();
    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            mListener = (QuitDialogListener) activity;
        }
        catch (Exception e){
            throw e;
        }
    }
}
