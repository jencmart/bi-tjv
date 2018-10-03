/*
 * (c) FIT CVUT
 */
package cz.cvut.fit.tjv.tictactoe.player;

/**
 * Player view
 * @author guthondr
 */
public class PlayerViewCLI implements PlayerView {
    private final Player player;

    public PlayerViewCLI(Player player) {
        this.player = player;
    }
    
    @Override
    public void printScore() {
        System.out.println("Player " + player + " score: " + player.getScore());
    }
    
    /**
     * Print out congratulations in case this player wins a game.
     */
    @Override
    public void printCongrats() {
        System.out.println("Congratulations to " + player);
    }
}
