package com.example.jimmyle.pacmanandroid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class UserInterface {
    //handles elements user interacts with
    private UserInterface(){}

    public static void drawArrowIndicators(BitmapImages bitmap, Movement movement, Canvas canvas, Paint paint, int blockSize, int currentArrowFrame) {
        switch(movement.getPacman().getNextDir()) {
            case(0):
                canvas.drawBitmap(bitmap.getArrowUp()[currentArrowFrame],5*blockSize , 20*blockSize, paint);
                break;
            case(1):
                canvas.drawBitmap(bitmap.getArrowRight()[currentArrowFrame],5*blockSize , 20*blockSize, paint);
                break;
            case(2):
                canvas.drawBitmap(bitmap.getArrowDown()[currentArrowFrame],5*blockSize , 20*blockSize, paint);
                break;
            case(3):
                canvas.drawBitmap(bitmap.getArrowLeft()[currentArrowFrame],5*blockSize , 20*blockSize, paint);
                break;
            default:
                break;
        }
    }

    public static void drawPauseButton(BitmapImages bitmap, Canvas canvas, Paint paint, int blockSize) {
        //draw pause button
        canvas.drawBitmap(bitmap.getPauseBitmap(), 0, 20*blockSize, paint);
    }

    public static void drawMuteButton(BitmapImages bitmap, Canvas canvas, Paint paint, int blockSize) {
        //draw mute button
        canvas.drawBitmap(bitmap.getMuteBitmap(), 0 * blockSize, 24*blockSize, paint);
    }

    // Method that draws pellets and updates them when eaten
    public static void drawPellets(Canvas canvas, short[][] currentMap, Paint paint, int blockSize) {
        float x, y;
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 17; j++) {
                x = j * blockSize;
                y = i * blockSize;
                // Draws pellet in the middle of a block
                if ((currentMap[i][j] & 16) != 0) {
                    canvas.drawCircle(x + blockSize / 2, y + blockSize / 2, blockSize / 10, paint);
                }
            }
        }
    }

    // Method to draw map layout that is based on the non-Android Pacman legacy project for CS56
    // add pause/unpause button
    // add mute/unmute button
    public static void drawMap(Canvas canvas, short[][] currentMap, Paint paint, int blockSize) {
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2.5f);
        int x, y;

        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 17; j++) {
                x = j * blockSize;
                y = i * blockSize;
                if ((currentMap[i][j] & 1) != 0) // draws left
                    canvas.drawLine(x, y, x, y + blockSize - 1, paint);

                if ((currentMap[i][j] & 2) != 0) // draws top
                    canvas.drawLine(x, y, x + blockSize - 1, y, paint);

                if ((currentMap[i][j] & 4) != 0) // draws right
                    canvas.drawLine(
                            x + blockSize, y, x + blockSize, y + blockSize - 1, paint);
                if ((currentMap[i][j] & 8) != 0) // draws bottom
                    canvas.drawLine(
                            x, y + blockSize, x + blockSize - 1, y + blockSize , paint);
            }
        }
        paint.setColor(Color.WHITE);
    }

    public static void drawScores(Canvas canvas, Paint paint, int blockSize) {
        paint.setTextSize(blockSize);

        Globals g = Globals.getInstance();
        GameConditions gc = GameConditions.getInstance();
        int currentScore = gc.getCurrentScore();
        int highScore = g.getHighScore();
        if (currentScore > highScore) {
            g.setHighScore(currentScore);
        }

        String formattedHighScore = String.format("%05d", highScore);
        String hScore = "High Score : " + formattedHighScore;
        canvas.drawText(hScore, 0, 2*blockSize - 10, paint);

        String formattedScore = String.format("%05d", currentScore);
        String score = "Score : " + formattedScore;
        canvas.drawText(score, 11 * blockSize, 2 * blockSize - 10, paint);
    }
}
