package com.mycompany.LHH.Controller;

import com.mycompany.LLH.DaoImpl.AdminDaoImpl;
import com.mycompany.LLH.DaoImpl.AdminNotFoundException;
import com.mycompany.LLH.ServiceImpl.AdminServiceImpl;
import com.mycompany.LLH.DaoImpl.BookingDaoImpl;
import com.mycompany.LLH.ServiceImpl.BookingServiceImpl;
import com.mycompany.LLH.ServiceImpl.PaymentServiceImpl;
import com.mycompany.LLH.Model.Admin;
import com.mycompany.LLH.Model.Booking;
import com.mycompany.LLH.Service.AdminService;
import com.mycompany.LLH.Service.BookingService;

import com.mycompany.LLH.LoginException.LoginException;
import com.mycompany.LLH.Model.Payment;
import com.mycompany.LLH.Service.PaymentService;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

    private final AdminService adminService = new AdminServiceImpl(new AdminDaoImpl());
    private final BookingService bookingService = new BookingServiceImpl(new BookingDaoImpl());
    private final PaymentService paymentService = new PaymentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("submit");

        switch (action) {
            case "viewBookings":
                List<Booking> bookings = bookingService.getAllBookings();
                request.setAttribute("bookings", bookings);
                request.getRequestDispatcher("viewBookings.jsp").forward(request, response);
                break;
            case "viewPayments":
                List<Payment> payments = paymentService.getAllPayments();
                request.setAttribute("payments", payments);
                request.getRequestDispatcher("viewPayments.jsp").forward(request, response);
                break;
            default:
       
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("submit");

        switch (action) {
            case "Login":
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                try {
                    request.getSession().setAttribute("admin", adminService.login(email, password));
                    request.getRequestDispatcher("Adminhome.jsp").forward(request, response);
                } catch (LoginException | AdminNotFoundException ex) {
                    request.setAttribute("error", ex.getMessage());
                    request.getRequestDispatcher("Adminlogin.jsp").forward(request, response);
                }
                break;
            case "updateBooking":
                int id = Integer.parseInt(request.getParameter("id"));
                String room = request.getParameter("room");
                Timestamp start = Timestamp.valueOf(request.getParameter("start"));
                Timestamp end = Timestamp.valueOf(request.getParameter("end"));
                int userId = Integer.parseInt(request.getParameter("user_id"));

                Booking booking = new Booking(room, start, end, userId);
                booking.setId(id);

                try {
                    bookingService.updateBookingDates(id, start, end);
                    request.setAttribute("message", "Booking updated successfully");
                } catch (Exception ex) {
                    request.setAttribute("error", "Error updating booking: " + ex.getMessage());
                }
                request.getRequestDispatcher("viewBookings.jsp").forward(request, response);
                break;
            default:
              
                if ("deleteBooking".equals(action)) {
                    int bookingIdToDelete = Integer.parseInt(request.getParameter("bookingId"));
                    boolean successDelete = bookingService.deleteBooking(bookingIdToDelete);

                    if (successDelete) {
                        request.setAttribute("message", "Booking deleted successfully");
                    } else {
                        request.setAttribute("error", "Error deleting booking");
                    }

                    List<Booking> updatedBookingsList = bookingService.getAllBookings();
                    request.setAttribute("bookings", updatedBookingsList);
                    request.getRequestDispatcher("viewBookings.jsp").forward(request, response);
                }
                break;
        }
    }
}
