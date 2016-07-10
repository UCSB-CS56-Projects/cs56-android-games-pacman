package com.example.jimmyle.pacmanandroid;

/**
 * Created by colerogers on 7/10/16.
 */
public class LevelGenerator {
    private static int width = 18;
    private static int height = 17;
    private short levelData[][];

    public LevelGenerator(){
        levelData = new short[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {19, 26, 26, 18, 26, 26, 26, 22, 0, 19, 26, 26, 26, 18, 26, 26, 22},
                {21, 0, 0, 21, 0, 0, 0, 21, 0, 21, 0, 0, 0, 21, 0, 0, 21},
                {17, 26, 26, 16, 26, 18, 26, 24, 26, 24, 26, 18, 26, 16, 26, 26, 20},
                {25, 26, 26, 20, 0, 25, 26, 22, 0, 19, 26, 28, 0, 17, 26, 26, 28},
                {0, 0, 0, 21, 0, 0, 0, 21, 0, 21, 0, 0, 0, 21, 0, 0, 0},
                {0, 0, 0, 21, 0, 19, 26, 24, 26, 24, 26, 22, 0, 21, 0, 0, 0},
                {26, 26, 26, 16, 26, 20, 0, 0, 0, 0, 0, 17, 26, 16, 26, 26, 26},
                {0, 0, 0, 21, 0, 17, 26, 26, 26, 26, 26, 20, 0, 21, 0, 0, 0},
                {0, 0, 0, 21, 0, 21, 0, 0, 0, 0, 0, 21, 0, 21, 0, 0, 0},
                {19, 26, 26, 16, 26, 24, 26, 22, 0, 19, 26, 24, 26, 16, 26, 26, 22},
                {21, 0, 0, 21, 0, 0, 0, 21, 0, 21, 0, 0, 0, 21, 0, 0, 21},
                {25, 22, 0, 21, 0, 0, 0, 17, 2, 20, 0, 0, 0, 21, 0, 19, 28}, // "2" in this line is for
                {0, 21, 0, 17, 26, 26, 18, 24, 24, 24, 18, 26, 26, 20, 0, 21, 0}, // pacman's spawn
                {19, 24, 26, 28, 0, 0, 25, 18, 26, 18, 28, 0, 0, 25, 26, 24, 22},
                {21, 0, 0, 0, 0, 0, 0, 21, 0, 21, 0, 0, 0, 0, 0, 0, 21},
                {25, 26, 26, 26, 26, 26, 26, 24, 26, 24, 26, 26, 26, 26, 26, 26, 28},};
    }
    public short[][] getLevelData(){return this.levelData;}

    public short[][] getTestMap(){
        //Map that has all pellets removed except for one
        return new short[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {3, 10, 10, 2, 10, 10, 10, 6, 0,3, 10,10,10,2,10,10, 6},
            {5, 0, 0, 5, 0, 0, 0, 5, 0, 5, 0, 0, 0, 5, 0, 0, 5},
            {1,10,10, 0,10,2,10, 8,10, 8,10,2,10, 0,10,10, 4},
            {9,10,10, 4, 0, 9,10, 6, 0,3,10, 12, 0, 1,10,10, 12},
            {0, 0, 0, 5, 0, 0, 0, 5, 0, 5, 0, 0, 0, 5, 0, 0, 0},
            {0, 0, 0, 5, 0,3,10, 8,10, 8,10, 6, 0, 5, 0, 0, 0},
            {10,10,10, 0,10, 4, 0, 0, 0, 0, 0, 1,10, 0,10,10,10},
            {0, 0, 0, 5, 0, 1,10,10,10,10,10, 4, 0, 5, 0, 0, 0},
            {0, 0, 0, 5, 0, 5, 0, 0, 0, 0, 0, 5, 0, 5, 0, 0, 0},
            {3,10,10, 0,10, 8,10, 6, 0,3,10, 8,10, 0,10,10, 6},
            {5, 0, 0, 5, 0, 0, 0, 5, 0, 5, 0, 0, 0, 5, 0, 0, 5},
            {9, 6, 0, 5, 0, 0, 0, 1, 2, 20, 0, 0, 0, 5, 0,3, 12}, // "2" in this line is for
            {0, 5, 0, 1,10,10,2, 8, 8, 8,2,10,10, 4, 0, 5, 0}, // pacman's spawn
            {3, 8,10, 12, 0, 0, 9,2,10,2, 12, 0, 0, 9, 10, 8, 6},
            {5, 0, 0, 0, 0, 0, 0, 5, 0, 5, 0, 0, 0, 0, 0, 0, 5},
            {9,10,10,10,10,10,10, 8,10, 8,10,10,10,10,10,10, 12},
    };
    }
}
