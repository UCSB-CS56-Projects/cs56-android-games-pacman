package com.example.jimmyle.pacmanandroid;

public class Globals{
    private static Globals instance;

    // Global variable
    private int highScore;
    private int levelSelector=1;

    // Restrict the constructor from being instantiated
    private Globals(){}

    public void setHighScore(int newScore){
        this.highScore =newScore;
    }
    public int getHighScore(){
        return this.highScore;
    }

    public void setLevelSelector(int newLevel){this.levelSelector=newLevel;}
    public int getLevelSelector(){return this.levelSelector;}

    public static synchronized Globals getInstance(){
        if(instance==null){
            instance=new Globals();
        }
        return instance;
    }

    public static void nextLevel(){
        Globals g = Globals.getInstance();

        if(g.getLevelSelector() != 3)
            g.setLevelSelector(g.getLevelSelector()+1);     //goes to next level, max 3
        else
            g.setLevelSelector(1);      //goes back to level 1
    }
}