/*
 * (c) FIT CVUT
 */
package cz.cvut.fit.tjv.tictactoe.game;

import cz.cvut.fit.tjv.tictactoe.player.Player;

/**
 * MnkGame view for command-line interface
 * @author Ondrej Guth
 */
public class GameViewCLI implements GameView {
    private final GameModel game;

    public GameViewCLI(GameModel game) {
        this.game = game;
    }
    
    /**
     * Print out the game grid
     */
    @Override
    public void displayGrid() {
        for (byte x = 0; x < game.getGridSize(); x++) {
            System.out.print("|");
            for (byte y = 0; y < game.getGridSize(); y++) {
                final Player p = game.getPlace(x, y);
                final String outStr;
                if (p == null)
                    outStr = " ";
                else if (game.isPlayerFirst(p))
                    outStr = "X";
                else
                    outStr = "O";
                System.out.print(outStr + "|");
            }
            System.out.println();
        }
    }
    
    /**
     * Print out current turn player
     */
    @Override
    public void displayCurrentPlayer() {
        System.out.println("Current player: " + game.getCurrentPlayer());
    }
    
    /**
     * Prompt the user for input to play the next turn. 
     * Also print out the game grid and the current player.
     */
    @Override
    public void displayRequestNextTurn() {
        displayGrid();
        displayCurrentPlayer();
        System.out.println("Next move? Enter as y x");
    }
    
    /**
     * Report error for invalid next turn input.
     */
    @Override
    public void displayBadMove() {
        System.out.println("Bad move, enter again");
    }
}
