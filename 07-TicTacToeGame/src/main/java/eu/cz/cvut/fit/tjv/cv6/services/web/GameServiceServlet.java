/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.tjv.cv6.services.web;

import eu.cz.cvut.fit.tjv.cv6.businessLogic.GameServiceBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jpavlicek
 */
@WebServlet(name = "GameServiceServlet", urlPatterns = {"/GameServiceServlet"})
public class GameServiceServlet extends HttpServlet {

    @EJB
    private GameServiceBean service;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //Rizeni servletu z server request - pokud zacina hra tak pracuji s metodou 
            // Servlet controls from server request
            if (request.getParameter("SetGame") != null) {
                service.intGame(request);
            }
            out.println("<html>");
            out.println("<head>");
            out.println("<title>TicTacToe game </title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<Form method=\"post\" action=\"GameServiceServlet\">");
            out.println("Move X: <input type=\"text\" name=\"X\" >");
            out.println("Move Y: <input type=\"text\" name=\"Y\" >");
            out.println("<input type=\"submit\" name =\"Run\" value=\"Run\">");
            // Second game part: settings game grid size
            if (request.getParameter("Run") != null) {
               out.print(service.nextMove(request));
            }
            out.println("</form>");
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
