/*
 * (c) FIT CVUT
 */
package cz.cvut.fit.tjv.tictactoe.player;

/**
 * Player view
 * @author guthondr
 */
public interface PlayerView {
    
    void printScore();
    
    /**
     * Print out congratulations in case this player wins a game.
     */
    void printCongrats();
}
