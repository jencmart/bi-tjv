/*
 * (c) FIT CVUT
 */
package cz.cvut.fit.tjv.tictactoe_cli.player;

/**
 * The game player (model).
 * @author Ondrej Guth
 */
public class Player {
    private final String name;
    private int score;
    private String type;
    
    public Player(final String name, String type) 
    {
        this.name = name;
        this.type = type;
    }
   
    
    public String getMark()
    {
        return this.type;
    }
    
    public String getName() {
        return name;
    }
    
    public int getScore() {
        return score;
    }
    
    public void increaseScore() {
        score++;
    }
    
    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return 67 * 5 + name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        return this.name.equals(other.name);
    }
    
    
}
