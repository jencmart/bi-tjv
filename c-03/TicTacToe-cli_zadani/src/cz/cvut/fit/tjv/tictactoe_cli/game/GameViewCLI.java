/*
 * (c) FIT CVUT
 */
package cz.cvut.fit.tjv.tictactoe_cli.game;

import cz.cvut.fit.tjv.tictactoe_cli.player.Player;

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
    public void displayGrid() 
    {
        for(int i = 0 ; i < this.game.getGridSize(); ++i)
        {
            System.out.print("|");
            for(int j = 0 ; j < this.game.getGridSize() ; ++j)
            {                
                if( this.game.getPlace((byte)i, (byte)j) != null)
                    System.out.print(this.game.getPlace((byte)i, (byte)j).getMark() +"|");
                
                    
                else
                    System.out.print(" |");
            }   
            System.out.println("");
        }
    }
    
    /**
     * Print out current turn player
     */
    @Override
    public void displayCurrentPlayer() 
    {
        System.out.println("\nPlayer [" + game.getCurrentPlayer().toString() + "][" + game.getCurrentPlayer().getMark() + "]on the turn....");
    }
    
    /**
     * Prompt the user for input to play the next turn. 
     * Also print out the game grid and the current player.
     */
    @Override
    public void displayRequestNextTurn() {
        displayGrid();
        displayCurrentPlayer();
        System.out.println("Next move? Enter as y x: ");
    }
    
    /**
     * Report error for invalid next turn input.
     */
    @Override
    public void displayBadMove() {
        System.out.println("Bad move, enter again");
    }
}
