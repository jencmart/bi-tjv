/*
 * (c) FIT CVUT
 */
package eu.cz.cvut.fit.tjv.cv6.tictactoe_cli.game;

import eu.cz.cvut.fit.tjv.cv6.tictactoe_cli.player.Player;

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
    public String displayGrid() {
        //Settings view table
        String grid = "<Table border=\"1\" width = \"400\" height=\"400\" bordercolor=\"black\">";
        for (byte x = 0; x < game.getGridSize(); x++) {
            grid+="<tr>";
            //System.out.print("|");
            for (byte y = 0; y < game.getGridSize(); y++) {
                grid+="<td>";
                final Player p = game.getPlace(x, y);
                final String outStr;
                if (p == null)
                    outStr = " ";
                else if (game.isPlayerFirst(p))
                    outStr = "X";
                
                else
                    outStr = "O";
                grid+=outStr+"<td>";
            }
            grid+="</tr>";
           
        }
        grid+="</table>";
        return grid;
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
    public String displayBadMove() {
        return ("<H1> Bad move, enter again </H1>");
    }
}
