/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.LHH.Controller;

import com.mycompany.LLH.Model.Payment;
import com.mycompany.LLH.Service.PaymentService;
import com.mycompany.LLH.ServiceImpl.PaymentServiceImpl;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends HttpServlet {

    private final PaymentServiceImpl paymentService = new PaymentServiceImpl(); // Implement this service

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roomType = request.getParameter("roomType");
        int numberOfNights = Integer.parseInt(request.getParameter("numberOfNights"));
        double totalCost = Double.parseDouble(request.getParameter("totalCostHidden"));

        Payment payment = new Payment(roomType, numberOfNights, totalCost);

        // Assuming your PaymentService has a method to add payment to the database
        boolean success = paymentService.addPayment(payment);

        if (success) {
            request.setAttribute("message", "Payment added successfully");
        } else {
            request.setAttribute("error", "Failed to add payment");
        }

        request.getRequestDispatcher("paymentConfirmation.jsp").forward(request, response);
    }
}