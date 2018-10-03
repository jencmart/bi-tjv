*
 * (c) FIT CVUT
 */
package cz.cvut.fit.tjv.tictactoe.db;

import cz.cvut.fit.tjv.tictactoe.player.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * Database class
 * 
 * @author Filip Glazar
 */
public class Db {

    private static Db instance = null;
    private final Connection conn;
    private final String url = "jdbc:derby://localhost:1527/tictactoe;user=app;password=app";

    protected Db() throws SQLException {
        conn = DriverManager.getConnection(url); ///nemuzu zavolat constructotr //jen muzu zavolat getInstance viz nize....
    }

    /**
     * Return instance of database class
     * @return Db
     */
    public static Db getInstance() {
        //TODO: implement
        if(instance == null) /// singleton jedinacek
            instance = new Db();
        return instance;
    }

    /**
     * Inserts score of given player into table
     * @param winner
     */
    public void addScore(Player winner) {
        //TODO: implement
        Statement st = getInstance().conn.createStatement();
        ResultSet rs = st.executeQuery("Select * from Customer");
        while(rs.next())
        {
            System.out.println(rs.getString(1));
        }
    }

    /**
     * Get aggregated score for given player name 
     * @param name
     * @return score
     */
    public int getScore(String name) {
        //TODO: implement
        return 0;
    }
    
    /**
     * Return aggregated score for all players
     * @return table
     */
    public Map<String, Integer> getScoreTable() {
        //TODO: implement
        return null;
    }
    
    public static void main(String[] args) {
        
    }
    
}
