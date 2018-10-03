/*
 * (c) FIT CVUT
 */
package cz.cvut.fit.tjv.tictactoe.game;

import cz.cvut.fit.tjv.tictactoe.player.Player;
import java.util.Scanner;

/**
 * Game controller for a command-line interface
 * @author Ondrej Guth
 */
public class GameControllerCLI implements GameController {
    private final GameModel game;
    private final GameView gameView;
    private final Scanner scanner;
    
    /**
     * Construct this object and create new game
     * @param scanner Scanner to read user input
     * @param player1 First player
     * @param player2 Second player
     */
    public GameControllerCLI(final Scanner scanner, final Player player1, final Player player2) {
            this.scanner = scanner;

            System.out.println("Game grid size?");
            final byte size = scanner.nextByte();
            System.out.println("Game n to win?");
            final byte nToWin = scanner.nextByte();
            game = new MnkGame(size, nToWin, player1, player2);
            
            /*
            game = new TicTacToe(player1, player2);
            */
            
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
    public void nextMove() {
        gameView.displayRequestNextTurn();
        
        boolean correntMove = true;
        
        do {        
            final byte x = scanner.nextByte();
            final byte y = scanner.nextByte();
            
            try {
                game.markAndWon(x, y);
            } catch (IllegalTurnException | ArrayIndexOutOfBoundsException e) {
                gameView.displayBadMove();
            }
        } while (!correntMove);
    }
    
    /**
     * Get if the game has finished
     * @return true if the game has finished, false otherwise
     */
    @Override
    public boolean isFinished() {
        return game.isFinished();
    }
}
