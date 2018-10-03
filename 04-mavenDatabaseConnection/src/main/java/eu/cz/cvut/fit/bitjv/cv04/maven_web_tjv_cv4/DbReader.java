/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bitjv.cv04.maven_web_tjv_cv4;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author kasalka1
 */
public class DbReader {
    private static DbReader instance = null;
    private static Connection conn = null;
    
    private DbReader() throws NamingException, SQLException {
        InitialContext it = new InitialContext();
        DataSource ds = (DataSource) it.lookup("jdbc/oracleTest");
        conn = ds.getConnection();
    }
    
    public static DbReader getInstance() throws NamingException, SQLException {
        if (instance == null) instance = new DbReader();
        return instance;
    }
    
    public static Connection getConnection() throws NamingException, SQLException {
        DbReader db = getInstance();
        return DbReader.conn;
    }
}
