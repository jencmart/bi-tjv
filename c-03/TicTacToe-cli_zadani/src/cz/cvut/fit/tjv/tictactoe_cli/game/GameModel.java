/*
 * (c) FIT CVUT
 */
package cz.cvut.fit.tjv.tictactoe_cli.game;

import cz.cvut.fit.tjv.tictactoe_cli.player.Player;

/**
 * The game board and its logic (model).
 *
 * @author Ondrej Guth
 */
public abstract class GameModel {

    private final Player player1, player2;
    private Player winning;
    private boolean currentIs1 = true;

    protected static enum Direction {
        NORTH((byte) 0, (byte) 1),
        SOUTH((byte) 0, (byte) -1),
        EAST((byte) 1, (byte) 0),
        WEST((byte) -1, (byte) 0),
        NE((byte) 1, (byte) 1),
        NW((byte) 1, (byte) -1),
        SE((byte) -1, (byte) 1),
        SW((byte) -1, (byte) -1);

        private final byte nextX, nextY;

        private Direction(byte nextX, byte nextY) {
            this.nextX = nextX;
            this.nextY = nextY;
        }

        public byte getNextX() {
            return nextX;
        }

        public byte getNextY() {
            return nextY;
        }
    }

    public GameModel(Player player1, Player player2) {
        if (player1 == null || player2 == null) {
            throw new IllegalArgumentException();
        }
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Play a turn, make the mark and check if the turn is the winning one.
     *
     * @param x an x-coordinate of the mark
     * @param y a y-coordinate of the mark
     * @return true if this turn is the winning one, false otherwise
     * @throws IllegalStateException Thrown if invoking this method when the
     * game has already finished
     * @throws IllegalTurnException Thrown if the (x,y) place is already marked
     * @throws ArrayIndexOutOfBoundsException Thrown if (x,y) is invalid
     */
    public abstract boolean markAndWon(final byte x, final byte y) throws IllegalStateException, IllegalTurnException, ArrayIndexOutOfBoundsException;

    /**
     * Get finish status
     *
     * @return true if the game has finished, false if still alive
     */
    public boolean isFinished() {
        return winning != null;
    }

    /**
     * Check if given player is the first player in this game.
     *
     * @param p the player to be compared
     * @return true if p is the first game player, false otherwise
     */
    public boolean isPlayerFirst(final Player p) {
        return player1.equals(p);
    }

    /**
     * Get player that won the game if finished.
     *
     * @return the player that has won the game or null
     */
    public Player getWinning() {
        return winning;
    }

    /**
     * Get player whose game turn is now (if not finished).
     *
     * @return current turn player or null if the game has finished
     */
    public Player getCurrentPlayer() {
        if (getWinning() != null) {
            return null;
        }
        return currentIs1 ? player1 : player2;
    }

    /**
     * Get the grid place
     */
    abstract Player getPlace(final byte x, final byte y);

    /**
     * Get size (width or height) of the game grid
     */
    public abstract byte getGridSize();

    /**
     * Set the player who wins this game.
     *
     * @param w the player who wins
     */
    protected void setWinning(final Player w) {
        winning = w;
    }

    /**
     * Flip the current player
     */
    protected void flipCurrent() {
        currentIs1 = !currentIs1;
    }

}
