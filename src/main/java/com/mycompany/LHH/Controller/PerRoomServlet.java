/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.LHH.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mycompany.LLH.DaoImpl.RoomDaoImpl;
import com.mycompany.LLH.Model.Room;
import com.mycompany.LLH.Service.RoomService;
import com.mycompany.LLH.ServiceImpl.RoomServiceImpl;
import com.mycompany.LLH.DaoImpl.PreferenceDaoImpl;
import com.mycompany.LLH.Service.PreferenceService;
import com.mycompany.LLH.ServiceImpl.PreferenceServiceImpl;

/**
 *
 * @author Train
 */
@WebServlet(name="PerRoomServlet", urlPatterns={"/PerRoomServlet"})
public class PerRoomServlet extends HttpServlet {
   
    private final RoomService rs = new RoomServiceImpl(new RoomDaoImpl());
    private final PreferenceService ps = new PreferenceServiceImpl(new PreferenceDaoImpl());
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PerRoomServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PerRoomServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Room room = new Room();
        room.setId(Integer.parseInt(request.getParameter("rooms")));
        request.setAttribute("room",rs.getRoom(room));
        request.setAttribute("preference",ps.getPreferences());
        request.getRequestDispatcher("Room.jsp").forward(request, response);

    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
