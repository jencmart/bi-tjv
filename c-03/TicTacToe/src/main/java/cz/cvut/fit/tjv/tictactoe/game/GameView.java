/*
 * (c) FIT CVUT
 */
package cz.cvut.fit.tjv.tictactoe.game;

/**
 * Game view
 * @author Ondrej Guth
 */
public interface GameView {
    /**
     * Print out the game grid
     */
    void displayGrid();
    
    /**
     * Print out current turn player
     */
    void displayCurrentPlayer();
    
    /**
     * Prompt the user for input to play the next turn. 
     * Also print out the game grid and the current player.
     */
    void displayRequestNextTurn();
    
    /**
     * Report error for invalid next turn input.
     */
    void displayBadMove();
}
