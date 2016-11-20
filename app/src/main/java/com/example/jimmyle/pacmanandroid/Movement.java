package com.example.jimmyle.pacmanandroid;

import android.content.Intent;
import android.content.Context;

/**
 * Created by colerogers on 7/25/16.
 */
public class Movement {
    //private instance vars
    private Pacman pacman;

    private Ghost ghost0;
    private Ghost ghost1;
    private Ghost ghost2;
    private Ghost ghost3;

    private int blockSize;
    private short [][] currentMap;
    private int swipeDir;
    private boolean pelletEaten;

    public Movement(final short [][] curMap, final int blockSize){
        currentMap = curMap;
        this.blockSize = blockSize;
        pacman = new Pacman(blockSize);
        ghost0 = new Ghost(blockSize);
        ghost1 = new Ghost(blockSize);
        ghost2 = new Ghost(blockSize);
        ghost3 = new Ghost(blockSize);

        swipeDir = 4;
        pelletEaten = false;
    }

    //public getters
    public Pacman getPacman(){ return pacman; }
    public Ghost getGhost0(){ return ghost0; }
    public Ghost getGhost1(){ return ghost1; }
    public Ghost getGhost2(){ return ghost2; }
    public Ghost getGhost3(){ return ghost3; }
    public int getSwipeDir(){ return swipeDir; }


    //checks to see if game over happens
    public void checkPlayerDeath() throws PlayerDeathException{
        if(((ghost0.getXPos()/blockSize) == (pacman.getXPos()/blockSize))&&
                ((ghost0.getYPos()/blockSize) == (pacman.getYPos()/blockSize)) ||
                ((ghost1.getXPos()/blockSize) == (pacman.getXPos()/blockSize)) &&
                ((ghost1.getYPos()/blockSize) == (pacman.getYPos()/blockSize)) ||
                ((ghost2.getXPos()/blockSize) == (pacman.getXPos()/blockSize)) &&
                ((ghost2.getYPos()/blockSize) == (pacman.getYPos()/blockSize)) ||
                ((ghost3.getXPos()/blockSize) == (pacman.getXPos()/blockSize)) &&
                ((ghost3.getYPos()/blockSize) == (pacman.getYPos()/blockSize))){
            throw new PlayerDeathException("Pacman and Ghost collision");
        }
    }

    //METHODS FOR PELLET REMOVAL
    //returns the updated map with the pellet removed
    public short[][] updateMap(){
        pelletEaten = false;
        return currentMap;
    }
    //to see if we need to update the map if a pellet was removed
    public boolean needMapRefresh(){ return pelletEaten; }
    //private method to change the map when the pellet is eaten
    private void pelletWasEaten(int x, int y, short value){
        currentMap[x][y] = value;
        pelletEaten = true;
    }

    //move pacman
    public void movePacman(){
        short ch;
        int nextDirection = pacman.getNextDir();
        int xPosPacman = pacman.getXPos();
        int yPosPacman = pacman.getYPos();

        // This was based on the non-Android Pacman legacy project for CS56
        // Check if xPos and yPos of pacman is both a multiple of block size
        if ( (xPosPacman % blockSize == 0) && (yPosPacman  % blockSize == 0) ) {

            // When pacman goes through tunnel on
            // the right reappear at left tunnel
            if (xPosPacman >= blockSize * 17) {
                xPosPacman = 0;
                pacman.setXPos(0);
            }

            // Is used to find the number in the level array in order to
            // check wall placement, pellet placement, and candy placement
            ch = currentMap[yPosPacman / blockSize][xPosPacman / blockSize];

            // If there is a pellet, eat it
            if ((ch & 16) != 0) {

                // Toggle pellet so it won't be drawn anymore
                pelletWasEaten(yPosPacman / blockSize, xPosPacman / blockSize, (short) (ch ^ 16));
            }

            // Checks for direction buffering
            if (!((nextDirection == 3 && (ch & 1) != 0) ||
                    (nextDirection == 1 && (ch & 4) != 0) ||
                    (nextDirection == 0 && (ch & 2) != 0) ||
                    (nextDirection == 2 && (ch & 8) != 0))) {
                pacman.setCurDir(nextDirection);
                swipeDir = nextDirection;
            }

            // Checks for wall collisions
            if ((swipeDir == 3 && (ch & 1) != 0) ||
                    (swipeDir == 1 && (ch & 4) != 0) ||
                    (swipeDir == 0 && (ch & 2) != 0) ||
                    (swipeDir == 2 && (ch & 8) != 0)) {
                swipeDir = 4;
            }
        }

        // When pacman goes through tunnel on
        // the left reappear at right tunnel
        if (xPosPacman < 0) {
            xPosPacman = blockSize * 17;
            pacman.setXPos(blockSize * 17);
        }
    }

    //call method after we have moved and drawn pacman
    public void updatePacman(){
        // Depending on the direction move the position of pacman
        if (swipeDir == 0) {
            pacman.setYPos(pacman.getYPos() + -blockSize/15);
        } else if (swipeDir == 1) {
            pacman.setXPos(pacman.getXPos() + blockSize/15);
        } else if (swipeDir == 2) {
            pacman.setYPos(pacman.getYPos() + blockSize/15);
        } else if (swipeDir == 3) {
            pacman.setXPos(pacman.getXPos() + -blockSize/15);
        }
    }


    //move ghost
    public void moveGhost0() {
        short ch;
        int ghostDir = ghost0.getDir();
        int xPosGhost = ghost0.getXPos();
        int yPosGhost = ghost0.getYPos();
        int xDis = pacman.getXPos() - xPosGhost;
        int yDis = pacman.getYPos() - yPosGhost;


        if ((xPosGhost % blockSize == 0) && (yPosGhost % blockSize == 0)) {
            ch = currentMap[yPosGhost / blockSize][xPosGhost / blockSize];

            if (xPosGhost >= blockSize * 17) {
                xPosGhost = 0;
                ghost0.setXPos(0);
            }
            if (xPosGhost < 0) {
                xPosGhost = blockSize * 17;
                ghost0.setXPos(blockSize * 17);
            }


            // Move ghost right and down
            if (xDis >= 0 && yDis >= 0) {
                if ((ch & 4) == 0 && (ch & 8) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        ghostDir = 1;
                        ghost0.setDir(1);
                    } else {
                        ghostDir = 2;
                        ghost0.setDir(2);
                    }
                } else if ((ch & 4) == 0) {
                    ghostDir = 1;
                    ghost0.setDir(1);
                } else if ((ch & 8) == 0) {
                    ghostDir = 2;
                    ghost0.setDir(2);
                } else {
                    ghostDir = 3;
                    ghost0.setDir(3);
                }
            }


            // Move ghost right and up
            if (xDis >= 0 && yDis <= 0) {
                if (xDis >= 0 && yDis <= 0) {
                    if ((ch & 4) == 0 && (ch & 2) == 0) {
                        if (Math.abs(xDis) > Math.abs(yDis)) {
                            ghostDir = 1;
                            ghost0.setDir(1);
                        } else {
                            ghostDir = 0;
                            ghost0.setDir(0);
                        }
                    } else if ((ch & 4) == 0) {
                        ghostDir = 1;
                        ghost0.setDir(1);
                    } else if ((ch & 2) == 0) {
                        ghostDir = 0;
                        ghost0.setDir(0);
                    } else {
                        ghostDir = 2;
                        ghost0.setDir(2);
                    }
                }
            }


            // Move ghost left and down
            if (xDis <= 0 && yDis >= 0) {
                if ((ch & 1) == 0 && (ch & 8) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        ghostDir = 3;
                        ghost0.setDir(3);
                    } else {
                        ghostDir = 2;
                        ghost0.setDir(2);
                    }
                } else if ((ch & 1) == 0) {
                    ghostDir = 3;
                    ghost0.setDir(3);
                } else if ((ch & 8) == 0) {
                    ghostDir = 2;
                    ghost0.setDir(2);
                } else {
                    ghostDir = 1;
                    ghost0.setDir(1);
                }
            }


            // Move ghost left and up
            if (xDis <= 0 && yDis <= 0) {
                if ((ch & 1) == 0 && (ch & 2) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        ghostDir = 3;
                        ghost0.setDir(3);
                    } else {
                        ghostDir = 0;
                        ghost0.setDir(0);
                    }
                } else if ((ch & 1) == 0) {
                    ghostDir = 3;
                    ghost0.setDir(3);
                } else if ((ch & 2) == 0) {
                    ghostDir = 0;
                    ghost0.setDir(0);
                } else {
                    ghostDir = 2;
                    ghost0.setDir(2);
                }
            }


            // Handles wall collisions
            if ((ghostDir == 3 && (ch & 1) != 0) ||
                    (ghostDir == 1 && (ch & 4) != 0) ||
                    (ghostDir == 0 && (ch & 2) != 0) ||
                    (ghostDir == 2 && (ch & 8) != 0)) {
                ghostDir = 4;
                ghost0.setDir(4);
            }
        }



            if (ghost0.getDir() == 0) {
                ghost0.setYPos(ghost0.getYPos() + -blockSize/20);
            } else if (ghost0.getDir() == 1) {
                ghost0.setXPos(ghost0.getXPos() + blockSize/20);
            } else if (ghost0.getDir() == 2) {
                ghost0.setYPos(ghost0.getYPos() + blockSize/20);
            } else if (ghost0.getDir() == 3) {
                ghost0.setXPos(ghost0.getXPos() + -blockSize/20);
            }
    }

    public void moveGhost1() {
        short ch;
        int ghostDir = ghost1.getDir();
        int xPosGhost = ghost1.getXPos();
        int yPosGhost = ghost1.getYPos();
        int xDis = pacman.getXPos() - xPosGhost;
        int yDis = pacman.getYPos() - yPosGhost;


        if ((xPosGhost % blockSize == 0) && (yPosGhost % blockSize == 0)) {
            ch = currentMap[yPosGhost / blockSize][xPosGhost / blockSize];

            if (xPosGhost >= blockSize * 17) {
                xPosGhost = 0;
                ghost1.setXPos(0);
            }
            if (xPosGhost < 0) {
                xPosGhost = blockSize * 17;
                ghost1.setXPos(blockSize * 17);
            }


            // Move ghost right and down
            if (xDis >= 0 && yDis >= 0) {
                if ((ch & 4) == 0 && (ch & 8) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        ghostDir = 1;
                        ghost1.setDir(1);
                    } else {
                        ghostDir = 2;
                        ghost1.setDir(2);
                    }
                } else if ((ch & 4) == 0) {
                    ghostDir = 1;
                    ghost1.setDir(1);
                } else if ((ch & 8) == 0) {
                    ghostDir = 2;
                    ghost1.setDir(2);
                } else {
                    ghostDir = 3;
                    ghost1.setDir(3);
                }
            }


            // Move ghost right and up
            if (xDis >= 0 && yDis <= 0) {
                if (xDis >= 0 && yDis <= 0) {
                    if ((ch & 4) == 0 && (ch & 2) == 0) {
                        if (Math.abs(xDis) > Math.abs(yDis)) {
                            ghostDir = 1;
                            ghost1.setDir(1);
                        } else {
                            ghostDir = 0;
                            ghost1.setDir(0);
                        }
                    } else if ((ch & 4) == 0) {
                        ghostDir = 1;
                        ghost1.setDir(1);
                    } else if ((ch & 2) == 0) {
                        ghostDir = 0;
                        ghost1.setDir(0);
                    } else {
                        ghostDir = 2;
                        ghost1.setDir(2);
                    }
                }
            }


            // Move ghost left and down
            if (xDis <= 0 && yDis >= 0) {
                if ((ch & 1) == 0 && (ch & 8) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        ghostDir = 3;
                        ghost1.setDir(3);
                    } else {
                        ghostDir = 2;
                        ghost1.setDir(2);
                    }
                } else if ((ch & 1) == 0) {
                    ghostDir = 3;
                    ghost1.setDir(3);
                } else if ((ch & 8) == 0) {
                    ghostDir = 2;
                    ghost1.setDir(2);
                } else {
                    ghostDir = 1;
                    ghost1.setDir(1);
                }
            }


            // Move ghost left and up
            if (xDis <= 0 && yDis <= 0) {
                if ((ch & 1) == 0 && (ch & 2) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        ghostDir = 3;
                        ghost1.setDir(3);
                    } else {
                        ghostDir = 0;
                        ghost1.setDir(0);
                    }
                } else if ((ch & 1) == 0) {
                    ghostDir = 3;
                    ghost1.setDir(3);
                } else if ((ch & 2) == 0) {
                    ghostDir = 0;
                    ghost1.setDir(0);
                } else {
                    ghostDir = 2;
                    ghost1.setDir(2);
                }
            }


            // Handles wall collisions
            if ((ghostDir == 3 && (ch & 1) != 0) ||
                    (ghostDir == 1 && (ch & 4) != 0) ||
                    (ghostDir == 0 && (ch & 2) != 0) ||
                    (ghostDir == 2 && (ch & 8) != 0)) {
                ghostDir = 4;
                ghost1.setDir(4);
            }
        }



        if (ghost1.getDir() == 0) {
            ghost1.setYPos(ghost1.getYPos() + -blockSize/20);
        } else if (ghost1.getDir() == 1) {
            ghost1.setXPos(ghost1.getXPos() + blockSize/20);
        } else if (ghost1.getDir() == 2) {
            ghost1.setYPos(ghost1.getYPos() + blockSize/20);
        } else if (ghost1.getDir() == 3) {
            ghost1.setXPos(ghost1.getXPos() + -blockSize/20);
        }
    }

    public void moveGhost2() {
        short ch;
        int ghostDir = ghost2.getDir();
        int xPosGhost = ghost2.getXPos();
        int yPosGhost = ghost2.getYPos();
        int xDis = pacman.getXPos() - xPosGhost;
        int yDis = pacman.getYPos() - yPosGhost;


        if ((xPosGhost % blockSize == 0) && (yPosGhost % blockSize == 0)) {
            ch = currentMap[yPosGhost / blockSize][xPosGhost / blockSize];

            if (xPosGhost >= blockSize * 17) {
                xPosGhost = 0;
                ghost2.setXPos(0);
            }
            if (xPosGhost < 0) {
                xPosGhost = blockSize * 17;
                ghost2.setXPos(blockSize * 17);
            }


            // Move ghost right and down
            if (xDis >= 0 && yDis >= 0) {
                if ((ch & 4) == 0 && (ch & 8) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        ghostDir = 1;
                        ghost2.setDir(1);
                    } else {
                        ghostDir = 2;
                        ghost2.setDir(2);
                    }
                } else if ((ch & 4) == 0) {
                    ghostDir = 1;
                    ghost2.setDir(1);
                } else if ((ch & 8) == 0) {
                    ghostDir = 2;
                    ghost2.setDir(2);
                } else {
                    ghostDir = 3;
                    ghost2.setDir(3);
                }
            }


            // Move ghost right and up
            if (xDis >= 0 && yDis <= 0) {
                if (xDis >= 0 && yDis <= 0) {
                    if ((ch & 4) == 0 && (ch & 2) == 0) {
                        if (Math.abs(xDis) > Math.abs(yDis)) {
                            ghostDir = 1;
                            ghost2.setDir(1);
                        } else {
                            ghostDir = 0;
                            ghost2.setDir(0);
                        }
                    } else if ((ch & 4) == 0) {
                        ghostDir = 1;
                        ghost2.setDir(1);
                    } else if ((ch & 2) == 0) {
                        ghostDir = 0;
                        ghost2.setDir(0);
                    } else {
                        ghostDir = 2;
                        ghost2.setDir(2);
                    }
                }
            }


            // Move ghost left and down
            if (xDis <= 0 && yDis >= 0) {
                if ((ch & 1) == 0 && (ch & 8) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        ghostDir = 3;
                        ghost2.setDir(3);
                    } else {
                        ghostDir = 2;
                        ghost2.setDir(2);
                    }
                } else if ((ch & 1) == 0) {
                    ghostDir = 3;
                    ghost2.setDir(3);
                } else if ((ch & 8) == 0) {
                    ghostDir = 2;
                    ghost2.setDir(2);
                } else {
                    ghostDir = 1;
                    ghost2.setDir(1);
                }
            }


            // Move ghost left and up
            if (xDis <= 0 && yDis <= 0) {
                if ((ch & 1) == 0 && (ch & 2) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        ghostDir = 3;
                        ghost2.setDir(3);
                    } else {
                        ghostDir = 0;
                        ghost2.setDir(0);
                    }
                } else if ((ch & 1) == 0) {
                    ghostDir = 3;
                    ghost2.setDir(3);
                } else if ((ch & 2) == 0) {
                    ghostDir = 0;
                    ghost2.setDir(0);
                } else {
                    ghostDir = 2;
                    ghost2.setDir(2);
                }
            }


            // Handles wall collisions
            if ((ghostDir == 3 && (ch & 1) != 0) ||
                    (ghostDir == 1 && (ch & 4) != 0) ||
                    (ghostDir == 0 && (ch & 2) != 0) ||
                    (ghostDir == 2 && (ch & 8) != 0)) {
                ghostDir = 4;
                ghost2.setDir(4);
            }
        }



        if (ghost2.getDir() == 0) {
            ghost2.setYPos(ghost2.getYPos() + -blockSize/20);
        } else if (ghost2.getDir() == 1) {
            ghost2.setXPos(ghost2.getXPos() + blockSize/20);
        } else if (ghost2.getDir() == 2) {
            ghost2.setYPos(ghost2.getYPos() + blockSize/20);
        } else if (ghost2.getDir() == 3) {
            ghost2.setXPos(ghost2.getXPos() + -blockSize/20);
        }
    }

    public void moveGhost3() {
        short ch;
        int ghostDir = ghost3.getDir();
        int xPosGhost = ghost3.getXPos();
        int yPosGhost = ghost3.getYPos();
        int xDis = pacman.getXPos() - xPosGhost;
        int yDis = pacman.getYPos() - yPosGhost;


        if ((xPosGhost % blockSize == 0) && (yPosGhost % blockSize == 0)) {
            ch = currentMap[yPosGhost / blockSize][xPosGhost / blockSize];

            if (xPosGhost >= blockSize * 17) {
                xPosGhost = 0;
                ghost3.setXPos(0);
            }
            if (xPosGhost < 0) {
                xPosGhost = blockSize * 17;
                ghost3.setXPos(blockSize * 17);
            }


            // Move ghost right and down
            if (xDis >= 0 && yDis >= 0) {
                if ((ch & 4) == 0 && (ch & 8) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        ghostDir = 1;
                        ghost3.setDir(1);
                    } else {
                        ghostDir = 2;
                        ghost3.setDir(2);
                    }
                } else if ((ch & 4) == 0) {
                    ghostDir = 1;
                    ghost3.setDir(1);
                } else if ((ch & 8) == 0) {
                    ghostDir = 2;
                    ghost3.setDir(2);
                } else {
                    ghostDir = 3;
                    ghost3.setDir(3);
                }
            }


            // Move ghost right and up
            if (xDis >= 0 && yDis <= 0) {
                if (xDis >= 0 && yDis <= 0) {
                    if ((ch & 4) == 0 && (ch & 2) == 0) {
                        if (Math.abs(xDis) > Math.abs(yDis)) {
                            ghostDir = 1;
                            ghost3.setDir(1);
                        } else {
                            ghostDir = 0;
                            ghost3.setDir(0);
                        }
                    } else if ((ch & 4) == 0) {
                        ghostDir = 1;
                        ghost3.setDir(1);
                    } else if ((ch & 2) == 0) {
                        ghostDir = 0;
                        ghost3.setDir(0);
                    } else {
                        ghostDir = 2;
                        ghost3.setDir(2);
                    }
                }
            }


            // Move ghost left and down
            if (xDis <= 0 && yDis >= 0) {
                if ((ch & 1) == 0 && (ch & 8) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        ghostDir = 3;
                        ghost3.setDir(3);
                    } else {
                        ghostDir = 2;
                        ghost3.setDir(2);
                    }
                } else if ((ch & 1) == 0) {
                    ghostDir = 3;
                    ghost3.setDir(3);
                } else if ((ch & 8) == 0) {
                    ghostDir = 2;
                    ghost3.setDir(2);
                } else {
                    ghostDir = 1;
                    ghost3.setDir(1);
                }
            }


            // Move ghost left and up
            if (xDis <= 0 && yDis <= 0) {
                if ((ch & 1) == 0 && (ch & 2) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        ghostDir = 3;
                        ghost3.setDir(3);
                    } else {
                        ghostDir = 0;
                        ghost3.setDir(0);
                    }
                } else if ((ch & 1) == 0) {
                    ghostDir = 3;
                    ghost3.setDir(3);
                } else if ((ch & 2) == 0) {
                    ghostDir = 0;
                    ghost3.setDir(0);
                } else {
                    ghostDir = 2;
                    ghost3.setDir(2);
                }
            }


            // Handles wall collisions
            if ((ghostDir == 3 && (ch & 1) != 0) ||
                    (ghostDir == 1 && (ch & 4) != 0) ||
                    (ghostDir == 0 && (ch & 2) != 0) ||
                    (ghostDir == 2 && (ch & 8) != 0)) {
                ghostDir = 4;
                ghost3.setDir(4);
            }
        }



        if (ghost3.getDir() == 0) {
            ghost3.setYPos(ghost3.getYPos() + -blockSize/20);
        } else if (ghost3.getDir() == 1) {
            ghost3.setXPos(ghost3.getXPos() + blockSize/20);
        } else if (ghost3.getDir() == 2) {
            ghost3.setYPos(ghost3.getYPos() + blockSize/20);
        } else if (ghost3.getDir() == 3) {
            ghost3.setXPos(ghost3.getXPos() + -blockSize/20);
        }
    }

    public void tryDeath(Context context){
        try{
            checkPlayerDeath();
        } catch (PlayerDeathException e){
            Intent failedIntent = new Intent(context, FailedLevelActivity.class);
            context.startActivity(failedIntent);
        }
    }
}//Movement
