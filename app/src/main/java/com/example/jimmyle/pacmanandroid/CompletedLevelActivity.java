package com.example.jimmyle.pacmanandroid;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

/**
 * Created by colerogers on 7/9/16.
 */
public class CompletedLevelActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sets up the view of the completed game screen
        setContentView(R.layout.completed_layout);
        MainActivity.getPlayer().start();
    }

    // Method to start activity for Help button
    public void showHelpScreen(View view) {
        Intent helpIntent = new Intent(this, HelpActivity.class);
        startActivity(helpIntent);
    }

    //Method to start activity for Settings button
    public void showSettingsScreen(View view){
        Intent settingIntent = new Intent(this,SettingsActivity.class);
        startActivity(settingIntent);
    }

    // Method to start activity for Play button
    public void showPlayScreen(View view) {
        Intent playIntent = new Intent(this, PlayActivity.class);
        startActivity(playIntent);
        PlayActivity.getInstance().finish();
        this.finish();
    }

    private static final String TAG = "SettingsActivity";
    public void playMusic(View view){
        if(MainActivity.getPlayer().isPlaying()){ MainActivity.getPlayer().stop(); }
        else{
            try {
                MainActivity.getPlayer().prepare();
            }
            catch(IOException ex){
                Log.d(TAG,"Prepare failed");
            }
            finally {
                MainActivity.getPlayer().start();
            }
        }
    }
}
