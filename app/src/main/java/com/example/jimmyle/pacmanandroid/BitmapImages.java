package com.example.jimmyle.pacmanandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;

/**
 * Created by colerogers on 7/25/16.
 */
public class BitmapImages {
    //private instance variables
    private int spriteSize, arrowSize, buttonSizeHeight, buttonSizeWidth;
    private Bitmap[] pacmanRight, pacmanDown, pacmanLeft, pacmanUp;
    private Bitmap[] arrowRight, arrowDown, arrowLeft, arrowUp;
    private Bitmap ghostBitmap0, ghostBitmap1, ghostBitmap2, ghostBitmap3;
    private Bitmap muteBitmap, pauseBitmap;

    public BitmapImages(int blockSize, Context context){
        spriteSize = blockSize;
        arrowSize = blockSize*7;
        buttonSizeHeight = blockSize*2;
        buttonSizeWidth = blockSize*4;

        // Add bitmap images of right arrow indicators
        arrowRight = new Bitmap[7]; // 7 image frames for right direction
        arrowRight[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.right_arrow_frame1), arrowSize, arrowSize, false);
        arrowRight[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.right_arrow_frame2), arrowSize, arrowSize, false);
        arrowRight[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.right_arrow_frame3), arrowSize, arrowSize, false);
        arrowRight[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.right_arrow_frame4), arrowSize, arrowSize, false);
        arrowRight[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.right_arrow_frame5), arrowSize, arrowSize, false);
        arrowRight[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.right_arrow_frame6), arrowSize, arrowSize, false);
        arrowRight[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.right_arrow_frame7), arrowSize, arrowSize, false);


        arrowDown = new Bitmap[7]; // 7 images frames for down direction
        arrowDown[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.down_arrow_frame1), arrowSize, arrowSize, false);
        arrowDown[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.down_arrow_frame2), arrowSize, arrowSize, false);
        arrowDown[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.down_arrow_frame3), arrowSize, arrowSize, false);
        arrowDown[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.down_arrow_frame4), arrowSize, arrowSize, false);
        arrowDown[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.down_arrow_frame5), arrowSize, arrowSize, false);
        arrowDown[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.down_arrow_frame6), arrowSize, arrowSize, false);
        arrowDown[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.down_arrow_frame7), arrowSize, arrowSize, false);


        arrowUp = new Bitmap[7]; // 7 frames for each direction
        arrowUp[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.up_arrow_frame1), arrowSize, arrowSize, false);
        arrowUp[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.up_arrow_frame2), arrowSize, arrowSize, false);
        arrowUp[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.up_arrow_frame3), arrowSize, arrowSize, false);
        arrowUp[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.up_arrow_frame4), arrowSize, arrowSize, false);
        arrowUp[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.up_arrow_frame5), arrowSize, arrowSize, false);
        arrowUp[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.up_arrow_frame6), arrowSize, arrowSize, false);
        arrowUp[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.up_arrow_frame7), arrowSize, arrowSize, false);


        arrowLeft = new Bitmap[7]; // 7 images frames for left direction
        arrowLeft[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.left_arrow_frame1), arrowSize, arrowSize, false);
        arrowLeft[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.left_arrow_frame2), arrowSize, arrowSize, false);
        arrowLeft[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.left_arrow_frame3), arrowSize, arrowSize, false);
        arrowLeft[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.left_arrow_frame4), arrowSize, arrowSize, false);
        arrowLeft[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.left_arrow_frame5), arrowSize, arrowSize, false);
        arrowLeft[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.left_arrow_frame6), arrowSize, arrowSize, false);
        arrowLeft[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.left_arrow_frame7), arrowSize, arrowSize, false);


        // Add bitmap images of pacman facing right
        pacmanRight = new Bitmap[4];
        pacmanRight[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(),R.drawable.pacman_right1), spriteSize, spriteSize, false);
        pacmanRight[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_right2), spriteSize, spriteSize, false);
        pacmanRight[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_right3), spriteSize, spriteSize, false);
        pacmanRight[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_right), spriteSize, spriteSize, false);


        // Add bitmap images of pacman facing down
        pacmanDown = new Bitmap[4];
        pacmanDown[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_down1), spriteSize, spriteSize, false);
        pacmanDown[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_down2), spriteSize, spriteSize, false);
        pacmanDown[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_down3), spriteSize, spriteSize, false);
        pacmanDown[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_down), spriteSize, spriteSize, false);


        // Add bitmap images of pacman facing left
        pacmanLeft = new Bitmap[4];
        pacmanLeft[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_left1), spriteSize, spriteSize, false);
        pacmanLeft[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_left2), spriteSize, spriteSize, false);
        pacmanLeft[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_left3), spriteSize, spriteSize, false);
        pacmanLeft[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_left), spriteSize, spriteSize, false);


        // Add bitmap images of pacman facing up
        pacmanUp = new Bitmap[4];
        pacmanUp[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_up1), spriteSize, spriteSize, false);
        pacmanUp[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_up2), spriteSize, spriteSize, false);
        pacmanUp[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_up3), spriteSize, spriteSize, false);
        pacmanUp[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_up), spriteSize, spriteSize, false);

        // Add bitmap image of mute
        muteBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.mute), buttonSizeWidth, buttonSizeHeight, false);

        // Add bitmap image of pause
        pauseBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pause), buttonSizeWidth, buttonSizeHeight, false);

        // Add bitmap image of ghost
        ghostBitmap0 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.ghost0), spriteSize, spriteSize, false);

        // Add bitmap image of ghost
        ghostBitmap1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.ghost1), spriteSize, spriteSize, false);

        // Add bitmap image of ghost
        ghostBitmap2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.ghost2), spriteSize, spriteSize, false);

        // Add bitmap image of ghost
        ghostBitmap3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.ghost3), spriteSize, spriteSize, false);
    }

    //public getters
    public Bitmap getPauseBitmap(){ return pauseBitmap; }
    public Bitmap getMuteBitmap(){ return muteBitmap; }

    public Bitmap[] getPacmanRight(){ return pacmanRight; }
    public Bitmap[] getPacmanLeft(){ return pacmanLeft; }
    public Bitmap[] getPacmanUp(){ return pacmanUp; }
    public Bitmap[] getPacmanDown(){ return pacmanDown; }

    public Bitmap getGhostBitmap0(){ return ghostBitmap0; }
    public Bitmap getGhostBitmap1(){ return ghostBitmap1; }
    public Bitmap getGhostBitmap2(){ return ghostBitmap2; }
    public Bitmap getGhostBitmap3(){ return ghostBitmap3; }

    public Bitmap[] getArrowRight(){ return arrowRight; }
    public Bitmap[] getArrowLeft(){ return arrowLeft; }
    public Bitmap[] getArrowUp(){ return arrowUp; }
    public Bitmap[] getArrowDown(){ return arrowDown; }
}
