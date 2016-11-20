package com.example.jimmyle.pacmanandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by colerogers on 7/25/16.
 */
public class Ghost {
    private int xPos, yPos, dir;

    public Ghost(int blockSize){
        //Ghost is at 8 position of length in the array x-dir
        //e.g. curMap[8,y]
        xPos = 8 * blockSize;

        //Ghost is at 4 position of height in the array y-dir
        //e.g. curMap[x,4]
        yPos = 4 * blockSize;

        dir = 4;
    }

    public int getXPos(){ return xPos; }
    public int getYPos(){ return yPos; }
    public int getDir(){ return dir; }

    public void setXPos(int xPos){ this.xPos = xPos; }
    public void setYPos(int yPos){ this.yPos = yPos; }
    public void setDir(int dir){ this.dir = dir; }

    public void drawGhost(BitmapImages bitmap, Canvas canvas, Movement movement, Paint paint, Context context, int type) {
       if (type == 0) {
           //move ghost
           movement.moveGhost0();
           //draw ghost
           canvas.drawBitmap(bitmap.getGhostBitmap0(), this.getXPos(), this.getYPos(), paint);
           //check if there is a collision and handle game over
           movement.tryDeath(context);
           }
        else if (type == 1) {
           //move ghost
           movement.moveGhost1();
           //draw ghost
           canvas.drawBitmap(bitmap.getGhostBitmap1(), this.getXPos(), this.getYPos(), paint);
           //check if there is a collision and handle game over
           movement.tryDeath(context);
           }
        else if (type == 2) {
           //move ghost
           movement.moveGhost2();
           //draw ghost
           canvas.drawBitmap(bitmap.getGhostBitmap2(), this.getXPos(), this.getYPos(), paint);
           //check if there is a collision and handle game over
           movement.tryDeath(context);
           }
        else if (type == 3) {
           //move ghost
           movement.moveGhost3();
           //draw ghost
           canvas.drawBitmap(bitmap.getGhostBitmap3(), this.getXPos(), this.getYPos(), paint);
           //check if there is a collision and handle game overDrawingView.tryDeath();
           movement.tryDeath(context);
        }
    }
}
