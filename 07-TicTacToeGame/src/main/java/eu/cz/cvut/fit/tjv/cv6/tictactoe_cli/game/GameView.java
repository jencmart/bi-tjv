/*
 * (c) FIT CVUT
 */
package eu.cz.cvut.fit.tjv.cv6.tictactoe_cli.game;

/**
 * Game view
 * @author Ondrej Guth
 */
public interface GameView {
    /**
     * Print out the game grid
     * @return 
     */
    public String displayGrid();
    
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
    public String displayBadMove();
}
