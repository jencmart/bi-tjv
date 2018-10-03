/*
 * (c) FIT CVUT
 */
package eu.cz.cvut.fit.tjv.cv6.tictactoe_cli.game;

/**
 * MnkGame controller
 * @author Ondrej Guth
 */
public interface GameController {    
    GameModel getGame();
    
    /**
     * Let user play the next turn, check if user input is valid
     */
    public String nextMove();
    
    /**
     * Get if the game has finished
     * @return true if the game has finished, false otherwise
     */
    public boolean isFinished();
    
    /** Gets view 
     * 
     * @return 
     */
    public GameView getGameView();
}
