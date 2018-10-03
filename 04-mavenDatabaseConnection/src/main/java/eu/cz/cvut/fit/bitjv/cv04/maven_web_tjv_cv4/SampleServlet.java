/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bitjv.cv04.maven_web_tjv_cv4;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kasalka1
 */
@WebServlet(name = "SampleServlet", urlPatterns = {"/SampleServlet"})
public class SampleServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws javax.naming.NamingException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SampleServlet</title>");            
            out.println("</head>"); 
            out.println("<body>");
            out.println("<h1>Servlet SampleServlet at " + request.getContextPath() + "</h1>");
                try {
                    Connection con = DbReader.getConnection();
                    PreparedStatement sp = con.prepareStatement("SELECT * FROM zakaznik");
                   // sp.setString(1, "Kladno");
                    ResultSet rs = sp.executeQuery();
                    
                    out.println("<Table><tr>");
                    
                    while (rs.next()) {
                        out.println("<tr><td>" +  rs.getString(2) + "</td><td>" + rs.getString(3) + "</td></tr>");
                    }
                    out.println("</table>");
                    
                    
                } catch (NamingException ex) {
                    Logger.getLogger(SampleServlet.class.getName()).log(Level.SEVERE, null, ex);
                    out.println("<B> Naming Excetpiont<B/>" + ex.toString());
                }
                 catch (SQLException ex) {
                    Logger.getLogger(SampleServlet.class.getName()).log(Level.SEVERE, null, ex);
                    out.println("<B> Naming Excetpiont<B/>" + ex.toString());
                }
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
