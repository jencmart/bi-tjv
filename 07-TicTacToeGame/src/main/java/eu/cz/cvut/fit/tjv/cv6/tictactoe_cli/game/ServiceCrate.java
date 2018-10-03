/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.tjv.cv6.tictactoe_cli.game;

/**
 *
 * @author jpavlicek
 * Transport object uset insetad Scanner
 */
public class ServiceCrate {
    private byte size;
    private byte nToWin;
    private byte X;
    private byte Y;

    public byte getSize() {
        return size;
    }

    public void setSize(byte size) {
        this.size = size;
    }

    public byte getnToWin() {
        return nToWin;
    }

    public void setnToWin(byte nToWin) {
        this.nToWin = nToWin;
    }

    public byte getX() {
        return X;
    }

    public void setX(byte X) {
        this.X = X;
    }

    public byte getY() {
        return Y;
    }

    public void setY(byte Y) {
        this.Y = Y;
    }
    
    
    
}
