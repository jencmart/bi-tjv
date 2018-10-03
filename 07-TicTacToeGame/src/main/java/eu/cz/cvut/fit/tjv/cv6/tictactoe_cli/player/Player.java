/*
 * (c) FIT CVUT
 */
package eu.cz.cvut.fit.tjv.cv6.tictactoe_cli.player;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The game player (model).
 * @author Ondrej Guth
 */
@XmlRootElement
public class Player implements Serializable {
    private String name;
    private int score;

    public Player() {
    }

    public Player(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
