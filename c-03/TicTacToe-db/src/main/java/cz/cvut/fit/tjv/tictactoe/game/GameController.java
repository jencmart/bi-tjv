/*
 * (c) FIT CVUT
 */
package cz.cvut.fit.tjv.tictactoe.game;

/**
 * MnkGame controller
 * @author Ondrej Guth
 */
public interface GameController {    
    GameModel getGame();
    
    /**
     * Let user play the next turn, check if user input is valid
     */
    void nextMove();
    
    /**
     * Get if the game has finished
     * @return true if the game has finished, false otherwise
     */
    boolean isFinished();
}
