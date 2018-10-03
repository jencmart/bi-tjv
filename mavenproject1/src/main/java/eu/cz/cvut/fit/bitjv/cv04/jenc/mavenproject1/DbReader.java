/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bitjv.cv04.jenc.mavenproject1;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author jencmart
 */
public class DbReader 
{
    private static DbReader instance = null;
    pricate static Connection conn = null;
    
    private DbReader() throws NamingException, SQLException 
    {
        InitalContext it = new InitialContext();
        DataSource ds = (DataSource)it.lookup("jdbc/test");
        conn = ds.getConnection();
    }
    
    /// singleton
    public static DbReader getInstance() throws NamingException, SQLException
    {
        if(instance != null)
        {
            return new DbReader();
        }
        return instance;
    }
    
    public static Connection getConnection() throws NamingException, SQLException
    {
        getInstance();
        return conn;
    }
    
        
    
    ///serverlet
    
    try
    {
        Connection con = DbReader.getConnection();
        PreparedStatement sp = con.prepareStatement("Select Name, Zip from Customer where city = ?");
        sp.setString(1,"Maiami");
        ResultSet rs =sp.executeQuery();
        out.println("<Table>");
        while(rs.next())
        {
            out.println("<tr><>td"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td></tr>");
        }
    }
    catch(NamingException ex)
    {
        Logger.getLogger(SampleSevrvlet.class.getName()).log(Level.SEVERE, null, ex);
        out.println("<B> Naming Exception<B/>"+ex.toString());
    }
    catch(SQLException ex)
    {
        Logger.getLogger(SampleSevrvlet.class.getName()).log(Level.SEVERE, null, ex);
        out.println("<B> Naming Exception<B/>"+ex.toString());
    }
   
}
