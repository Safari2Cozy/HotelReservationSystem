/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.LHH.Controller;

import com.mycompany.LLH.Dao.PreferenceDao;
import com.mycompany.LLH.DaoImpl.PreferenceDaoImpl;
import com.mycompany.LLH.Model.Preference;
import com.mycompany.LLH.Service.PreferenceService;
import com.mycompany.LLH.ServiceImpl.PreferenceServiceImpl;
import com.mycompany.LLH.DaoImpl.RoomDaoImpl;
import com.mycompany.LLH.Model.Room;
import com.mycompany.LLH.Service.RoomService;
import com.mycompany.LLH.ServiceImpl.RoomServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Train
 */
@WebServlet(name = "RoomServlet", urlPatterns = {"/RoomServlet"})
public class RoomServlet extends HttpServlet {

    private final RoomService roomService = new RoomServiceImpl(new RoomDaoImpl());

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Room> rooms = roomService.getRooms();
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("Room.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Room Servlet";
    }
}