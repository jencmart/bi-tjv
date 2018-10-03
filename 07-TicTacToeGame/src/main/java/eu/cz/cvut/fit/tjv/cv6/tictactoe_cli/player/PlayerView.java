/*
 * (c) FIT CVUT
 */
package eu.cz.cvut.fit.tjv.cv6.tictactoe_cli.player;

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
