/*
 * (c) FIT CVUT
 */
package cz.cvut.fit.tjv.tictactoe_cli.player;

/**
 * Player view
 * @author guthondr
 */
public class PlayerViewCLI implements PlayerView {
    private final Player player;
    
    public PlayerViewCLI(Player player)
    {
     this.player = player;   
    }
    
    @Override
    public void printScore() 
    {
        System.out.println("Score is: " + this.player.getScore());
    }

    @Override
    public void printCongrats() {
          System.out.println(this.player.getName()  + ": Congratz bro, yo win!");
    }
    
    //TODO: implement
}
