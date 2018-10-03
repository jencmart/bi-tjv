/*
 * (c) FIT CVUT
 */
package eu.cz.cvut.fit.tjv.cv6.tictactoe_cli.game;

import eu.cz.cvut.fit.tjv.cv6.tictactoe_cli.player.Player;
import java.util.Scanner;

/**
 * Game controller for a command-line interface
 * @author Ondrej Guth
 */
public class GameControllerCLI implements GameController {
    private final GameModel game;
    private final GameView gameView;
    private final ServiceCrate service;
    
    /**
     * Construct this object and create new game
     * @param player1 First player
     * @param player2 Second player
     */
    public GameControllerCLI(final ServiceCrate service, final Player player1, final Player player2) {
            this.service =  service;
            final byte size =  service.getSize();
            final byte nToWin =  service.getnToWin();
            
            game = new MnkGame(size, nToWin, player1, player2);
            gameView = new GameViewCLI(game);
    }
    
    @Override
    public GameModel getGame() {
        return game;
    }
    
    /**
     * Let user play the next turn, check if user input is valid
     */
    @Override
    public String nextMove() {
        gameView.displayRequestNextTurn();
        
        boolean correntMove = true;
        
        do {        
            final byte x = service.getX();
            final byte y = service.getY();
            
            try {
                game.markAndWon(x, y);
            } catch (IllegalTurnException | ArrayIndexOutOfBoundsException e) {
              return  gameView.displayBadMove();
            }
        } while (!correntMove);
        
        return "OK";
    }
    
    /**
     * Get if the game has finished
     * @return true if the game has finished, false otherwise
     */
    @Override
    public boolean isFinished() {
        return game.isFinished();
    }

    public GameView getGameView() {
        return gameView;
    }
    
    
}
