package com.example.jimmyle.pacmanandroid;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by M on 11/19/2016.
 */

public class GameConditions {

    private static GameConditions instance;
    private int currentScore = 0;           //Current game score
    private int numOfPellets;           //Total number of pellets remaining

    private GameConditions() {}

    public void updateCurrentScore()   {
        currentScore += 10;
    }

    public void setCurrentScore(int newScore)   {
        currentScore = newScore;
    }

    public int getCurrentScore()    {
        return currentScore;
    }

    public void updateNumOfPellets() {
        numOfPellets--;
    }

    public void setNumOfPellets(int newNum) {
        numOfPellets =newNum;
    }

    public int getNumOfPellets()    {
        return numOfPellets;
    }

    public static synchronized GameConditions getInstance(){
        if(instance==null){
            instance=new GameConditions();
        }
        return instance;
    }

    //counts the number of pellets at the start of the game
    public static void countPellets(short[][] currentMap) {
        GameConditions gc = GameConditions.getInstance();
        gc.setNumOfPellets(0);
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 17; j++) {
                if ((currentMap[i][j] & 16) != 0) {
                    //increases the total number of pellets
                    gc.setNumOfPellets(gc.getNumOfPellets() + 1);;

                    Log.i("info", "Pellets = " + Integer.toString(gc.getNumOfPellets()));
                }
            }
        }
    }

    public static void checkVictory(Context context) {
        GameConditions gc = GameConditions.getInstance();
        if (gc.getNumOfPellets() == 0) {
            Log.i("info", "Level completed - GameOver");

            //go to next level
            Globals.nextLevel();

            Intent completedIntent = new Intent(context, CompletedLevelActivity.class);
            context.startActivity(completedIntent);
        }
    }

    public static void resetCurrentScore()   {
        GameConditions gc = GameConditions.getInstance();
        gc.setCurrentScore(0);
    }
}