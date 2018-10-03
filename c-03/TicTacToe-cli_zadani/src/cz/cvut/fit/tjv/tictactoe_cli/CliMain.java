/* (c) FIT CVUT, 2017 
 */
package cz.cvut.fit.tjv.tictactoe_cli;

import cz.cvut.fit.tjv.tictactoe_cli.game.GameController;
import cz.cvut.fit.tjv.tictactoe_cli.game.GameControllerCLI;
import cz.cvut.fit.tjv.tictactoe_cli.player.Player;
import cz.cvut.fit.tjv.tictactoe_cli.player.PlayerView;
import cz.cvut.fit.tjv.tictactoe_cli.player.PlayerViewCLI;
import java.util.Scanner;

/**
 * Main class
 *
 * @author Ondrej Guth
 */
public final class CliMain {

    private final Scanner scanner;
    private final Player player1, player2;
    private final PlayerView player1View, player2View;

    public static void main(String[] args) {
        new CliMain();
    }

    /**
     * Create an instance of this class, start CLI of the application
     */
    CliMain() {
        scanner = new Scanner(System.in);

        System.out.println("Enter player1's name");
        player1 = initPlayer(scanner, "x");

        System.out.println("Enter player2's name");
        player2 = initPlayer(scanner, "o");

        player1View = new PlayerViewCLI(player1);
        player2View = new PlayerViewCLI(player2);

        run();
    }

    /**
     * Define behaviour of the application
     */
    void run() {
        do {
            try {
//TODO uncomment the follo
                final GameController gameController = new GameControllerCLI(scanner, player1, player2);

                do {
                    gameController.nextMove();
                } while (!gameController.isFinished());

                final Player winner = gameController.getGame().getWinning();
                winner.increaseScore();
                if (winner == player1) {
                    player1View.printCongrats();
                } else {
                    player2View.printCongrats();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("You entered illegal game settings.");
            }

            player1View.printScore();
            player2View.printScore();

            System.out.println("Next game (Y/N)?");
        } while (scanner.hasNext() && scanner.next().equalsIgnoreCase("Y"));
    }

    /**
     * Create new Player, based on input
     *
     * @param s scanner to read input
     * @return new instance of Player
     */
    private static Player initPlayer(final Scanner s, String type) {
        String buffer;
        buffer = s.next();
        return new Player(buffer, type);
    }
}
