package com.example.jimmyle.pacmanandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class DrawingView extends SurfaceView implements Runnable, SurfaceHolder.Callback {
    private Thread thread;
    private SurfaceHolder holder;
    private boolean canDraw = true;

    private Paint paint;

    private int totalFrame = 4;             // Total amount of frames fo each direction
    private int currentPacmanFrame = 0;     // Current Pacman frame to draw
    private int currentArrowFrame = 0;      // Current arrow frame to draw
    private long frameTicker;               // Current time since last frame has been drawn

    private float x1, x2, y1, y2;           // Initial/Final positions of swipe

    private int screenWidth;                // Width of the phone screen
    private int blockSize;                  // Size of a block on the map
    public static int LONG_PRESS_TIME=750;  // Time in milliseconds
    final Handler handler = new Handler();

    //Added Variables by Cole
    private short currentMap[][];           //the current map being played

    //refactor of DrawingView methods into separate objects/classes
    private Movement movement;
    private Pacman pacman;
    private Ghost ghost0;
    private Ghost ghost1;
    private Ghost ghost2;
    private Ghost ghost3;
    private BitmapImages bitmap;


    public DrawingView(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        frameTicker = 1000/totalFrame;
        paint = new Paint();
        paint.setColor(Color.WHITE);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;

        //takes screen width of device and creates 17 equal block sizes in length x-dir
        blockSize = screenWidth/17;
        blockSize = (blockSize / 5) * 5;

        Globals map = Globals.getInstance();
        int theMap = map.getLevelSelector();
        currentMap = LevelGenerator.getMap(theMap);       //gets the map global var
        //currentMap = LevelGenerator.getMap(0);            //gets the test map
        //currentMap = LevelGenerator.getMap(1);          //gets the first level

        movement = new Movement(currentMap, blockSize);   //create a new instance of the movement class

        pacman = movement.getPacman();                    //reference of pacman object
        ghost0 = movement.getGhost0();                      //reference of ghost object 0
        ghost1 = movement.getGhost1();                      //reference of ghost object 1
        ghost2 = movement.getGhost2();                      //reference of ghost object 2
        ghost3 = movement.getGhost3();                      //reference of ghost object 3

        //each ghost spawns at a different position
        ghost1.setXPos(4 * blockSize);
        ghost1.setYPos(8 * blockSize);
        ghost1.setDir(1);
        ghost2.setXPos(12 * blockSize);
        ghost2.setYPos(8 * blockSize);
        ghost2.setDir(2);
        ghost3.setXPos(4 * blockSize);
        ghost3.setYPos(0 * blockSize);
        ghost3.setDir(3);


        bitmap = new BitmapImages(blockSize, context);    //loads the bitmap images

        Log.i("info", "Constructor");


    }

    @Override
    public void run() {
        Log.i("info", "Run");
        GameConditions.countPellets(currentMap);
        while (canDraw) {
            if (!holder.getSurface().isValid()) {
                continue;
            }
            Canvas canvas = holder.lockCanvas();
            // Set background color to Transparent
            if (canvas != null) {
                canvas.drawColor(Color.BLACK);
                UserInterface.drawMap(canvas, currentMap, paint, blockSize);
                updateFrame(System.currentTimeMillis());
                ghost0.drawGhost(bitmap, canvas, movement, paint, getContext(), 0);
                ghost1.drawGhost(bitmap, canvas, movement, paint, getContext(), 1);
                ghost2.drawGhost(bitmap, canvas, movement, paint, getContext(), 2);
                ghost3.drawGhost(bitmap, canvas, movement, paint, getContext(), 3);
                pacman.drawPacman(bitmap, canvas, movement, paint, getContext(), currentPacmanFrame);
                updateConditions(movement);
                GameConditions.checkVictory(getContext());
                UserInterface.drawArrowIndicators(bitmap, movement, canvas, paint, blockSize, currentArrowFrame);
                UserInterface.drawPellets(canvas, currentMap, paint, blockSize);
                UserInterface.drawPauseButton(bitmap, canvas, paint, blockSize);
                UserInterface.drawMuteButton(bitmap, canvas, paint, blockSize);
                UserInterface.drawScores(canvas, paint, blockSize);
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    Runnable pauseThread = new Runnable() {
        public void run() {
            Log.i("info", "pauseThread");
            Intent pauseIntent = new Intent(getContext(), PauseActivity.class);
            getContext().startActivity(pauseIntent);
        }
    };

    Runnable musicThread = new Runnable() {
        public void run() {
            Log.i("info", "musicThread");
            if (MainActivity.getPlayer().isPlaying())
                MainActivity.getPlayer().pause();
            else
                MainActivity.getPlayer().start();
        }
    };

    // Method to get touch events
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case (MotionEvent.ACTION_DOWN): {
                x1 = event.getX();
                y1 = event.getY();
                if (x1 >= blockSize*0 && x1 <= blockSize*4 && y1 >= blockSize*20 && y1 <= blockSize*22) {
                    handler.postAtFrontOfQueue(pauseThread);
                 }
                if (x1 >= blockSize*0 && x1 <= blockSize*4 && y1 >= blockSize*24 && y1 <= blockSize*26) {
                    handler.postAtFrontOfQueue(musicThread);
                }
                break;
            }
            case (MotionEvent.ACTION_UP): {
                x2 = event.getX();
                y2 = event.getY();
                calculateSwipeDirection();
                if (x1 >= blockSize*0 && x1 <= blockSize*4 && y1 >= blockSize*20 && y1 <= blockSize*22) {
                    handler.removeCallbacks(pauseThread);
                }
                if (x1 >= blockSize*0 && x1 <= blockSize*4 && y1 >= blockSize*24 && y1 <= blockSize*26) {
                    handler.removeCallbacks(musicThread);
                }
                break;
            }
        }
        return true;
    }

    // Calculates which direction the user swipes
    // based on calculating the differences in
    // initial position vs final position of the swipe
    private void calculateSwipeDirection() {
        float xDiff = (x2 - x1);
        float yDiff = (y2 - y1);

        // Directions
        // 0 means going up
        // 1 means going right
        // 2 means going down
        // 3 means going left
        // 4 means stop moving, look at move function

        // Checks which axis has the greater distance
        // in order to see which direction the swipe is
        // going to be (buffering of direction)
        if (Math.abs(yDiff) > Math.abs(xDiff)) {
            if (yDiff < 0) {
                pacman.setNextDir(0);
            } else if (yDiff > 0) {
                pacman.setNextDir(2);
            }
        } else {
            if (xDiff < 0) {
                pacman.setNextDir(3);
            } else if (xDiff > 0) {
                pacman.setNextDir(1);
            }
        }
    }

    private void updateConditions(Movement movement) {
        if (movement.needMapRefresh()) {
            GameConditions gc = GameConditions.getInstance();
            currentMap = movement.updateMap();
            gc.updateCurrentScore();
            gc.updateNumOfPellets();
        }
    }


    // Check to see if we should update the current frame
    // based on time passed so the animation won't be too
    // quick and look bad
    private void updateFrame(long gameTime) {

        // If enough time has passed go to next frame
        if (gameTime > frameTicker + (totalFrame * 30)) {
            frameTicker = gameTime;

            // Increment the frame
            currentPacmanFrame++;
            // Loop back the frame when you have gone through all the frames
            if (currentPacmanFrame >= totalFrame) {
                currentPacmanFrame = 0;
            }
        }
        if (gameTime > frameTicker + (50)) {
            currentArrowFrame++;
            if (currentArrowFrame >= 7) {
                currentArrowFrame = 0;
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i("info", "Surface Created");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i("info", "Surface Changed");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i("info", "Surface Destroyed");
    }

    public void pause() {
        Log.i("info", "pause");
        canDraw = false;
        thread = null;
    }

    public void resume() {
        Log.i("info", "resume");
        if (thread != null) {
            thread.start();
        }
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
            Log.i("info", "resume thread");
        }
        canDraw = true;
    }
}
