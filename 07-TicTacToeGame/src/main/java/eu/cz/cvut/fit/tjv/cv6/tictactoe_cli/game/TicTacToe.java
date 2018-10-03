/*
 * (c) FIT CVUT
 */

package eu.cz.cvut.fit.tjv.cv6.tictactoe_cli.game;

import eu.cz.cvut.fit.tjv.cv6.tictactoe_cli.player.Player;

/**
 * The original tic-tac-toe game (model).
 * @author Ondrej Guth
 */
public class TicTacToe extends GameModel {
    private final Player[][] grid = new Player[3][3];
    private final byte N_TO_WIN = 3;
    

    /**
     * 
     * @param player1 first player
     * @param player2 second player
     */
    public TicTacToe(final Player player1, final Player player2) {
        super(player1, player2);
    }

    /**
     * Play a turn, make the mark and check if the turn is the winning one.
     * @param x an x-coordinate of the mark
     * @param y a y-coordinate of the mark
     * @return true if this turn is the winning one, false otherwise
     * @throws IllegalStateException Thrown if invoking this method when the game has already finished
     * @throws IllegalTurnException Thrown if the (x,y) place is already marked
     * @throws ArrayIndexOutOfBoundsException Thrown if (x,y) is invalid
     */
    @Override
    public boolean markAndWon(final byte x, final byte y) throws IllegalStateException, IllegalTurnException, ArrayIndexOutOfBoundsException {
        boolean result = false;
        if (getWinning() != null)
            throw new IllegalStateException();
        
        if (grid[x][y] != null)
            throw new IllegalTurnException();
        
        grid[x][y] = getCurrentPlayer();

        
        if (max(
                nToDirection(Direction.NORTH, x, y, getCurrentPlayer()),
                nToDirection(Direction.SOUTH, x, y, getCurrentPlayer()),
                nToDirection(Direction.WEST, x, y, getCurrentPlayer()),
                nToDirection(Direction.EAST, x, y, getCurrentPlayer()),
                nToDirection(Direction.NW, x, y, getCurrentPlayer()),
                nToDirection(Direction.NE, x, y, getCurrentPlayer()),
                nToDirection(Direction.SE, x, y, getCurrentPlayer()),
                nToDirection(Direction.SW, x, y, getCurrentPlayer())
        ) >= N_TO_WIN) {
            result = true;
            setWinning(getCurrentPlayer());
        } else {
            flipCurrent();
        }

        return result;        
    }
    
    private static byte max(byte... values) {
        byte maximum = Byte.MIN_VALUE;
        
        for (final byte value : values) {
            if (value > maximum)
                maximum = value;
        }
        
        return maximum;
    }
    
    private byte nToDirection(final Direction dir, final byte x, final byte y, final Player whoseMark) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length || grid[x][y] != whoseMark) {
            return 0;
        } else {
            final byte nextX, nextY;
            
            nextX = (byte)(x + dir.getNextX());
            nextY = (byte)(y + dir.getNextY());

            return (byte)(1 + nToDirection(dir, nextX, nextY, whoseMark));
        }
    }

    /**
     * Get the grid place
     */
    @Override
    Player getPlace(final byte x, final byte y) {
        return grid[x][y];
    }
    
    /**
     * Get size (width or height) of the game grid
     */
    @Override
    public byte getGridSize() {
        return (byte)grid.length;
    }
    
}
