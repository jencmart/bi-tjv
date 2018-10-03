/*
 * (c) FIT CVUT
 */

package cz.cvut.fit.tjv.tictactoe_cli.game;

import cz.cvut.fit.tjv.tictactoe_cli.player.Player;
import java.util.ArrayList;
import java.util.Vector;

/**
 * The original tic-tac-toe game (model). See also 
 * @author Ondrej Guth
 */
public class TicTacToe extends GameModel 
{
    private Vector<Vector<Integer>> a;
    public TicTacToe(Player player1, Player player2) 
    {
        super(player1, player2);
        this.a = new Vector<>(3,3);
    }

    @Override
    public boolean markAndWon(byte x, byte y) throws IllegalStateException, IllegalTurnException, ArrayIndexOutOfBoundsException 
    {
        //todo
    }

    @Override
    Player getPlace(byte x, byte y) 
    {
       //todo
    }

    @Override
    public byte getGridSize() 
    {
      //todo
    }
    //TODO: implement
    
}
