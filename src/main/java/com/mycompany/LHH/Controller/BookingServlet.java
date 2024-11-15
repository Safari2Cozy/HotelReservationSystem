package com.mycompany.LHH.Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import com.mycompany.LLH.Dao.BookingDao;
import com.mycompany.LLH.DaoImpl.BookingDaoImpl;
import com.mycompany.LLH.ServiceImpl.BookingServiceImpl;
import com.mycompany.LLH.Model.Booking;
import com.mycompany.LLH.Model.User;
import com.mycompany.LLH.Service.BookingService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Train
 */
@WebServlet(name = "BookingServlet", urlPatterns = {"/BookingServlet"})
public class BookingServlet extends HttpServlet {

    private BookingService bookingService;

    @Override
    public void init() throws ServletException {
        super.init();
        BookingDao bookingDao = new BookingDaoImpl();
        bookingService = new BookingServiceImpl(bookingDao);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Retrieve form parameters
        String roomType = request.getParameter("roomType");
        Timestamp start = Timestamp.valueOf(request.getParameter("startDate") + " 00:00:00");
        Timestamp end = Timestamp.valueOf(request.getParameter("endDate") + " 00:00:00");
        int userId = ((User) request.getSession().getAttribute("user")).getId();
        double totalCost = Double.parseDouble(request.getParameter("totalCost"));

        // Create Booking object
        Booking booking = new Booking(roomType, start, end, userId);

        // Call BookingService to add the booking
        boolean bookingSuccess = bookingService.addBooking(booking);

        // Redirect to BookingConfirmation.jsp
        if (bookingSuccess) {
            // Set attributes for BookingConfirmation.jsp
            request.setAttribute("totalCost", totalCost);
            request.getRequestDispatcher("BookingConfirmation.jsp").forward(request, response);
        } else {
            // Handle booking failure
            PrintWriter out = response.getWriter();
            out.println("<html><body><h3>Booking failed. Please try again.</h3></body></html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response); // Handle GET requests similarly to POST
    }
}

