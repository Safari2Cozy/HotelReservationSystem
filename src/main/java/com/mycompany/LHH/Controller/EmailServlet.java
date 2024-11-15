/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.LHH.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
@WebServlet(name = "EmailServlet", urlPatterns = {"/EmailServlet"})
public class EmailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String totalCost = request.getParameter("totalCost");

        HttpSession session = request.getSession(false); // Do not create a new session if not exists
        if (session != null && session.getAttribute("userEmail") != null) {
            String userEmail = (String) session.getAttribute("userEmail");

            boolean emailSent = sendConfirmationEmail(userEmail, totalCost);

            if (emailSent) {
                response.getWriter().println("Confirmation email sent successfully to " + userEmail);
            } else {
                response.getWriter().println("Failed to send confirmation email.");
            }
        } else {
            response.getWriter().println("User email not found in session.");
        }
    }

    private boolean sendConfirmationEmail(String userEmail, String totalCost) {
        // Sender's email and password
        final String username = "nk.maisela@gmail.com";
        final String password = "nixx ogrx yehj bdek";

        // SMTP server settings
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP server address
        props.put("mail.smtp.port", "587"); // Replace with your SMTP port number (typically 587)

        // Create a Session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a new message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username, "Luxury Leisure Hotel"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Booking Confirmation");
            message.setText("Dear Customer,\n\n"
                            + "Your booking has been confirmed with a total cost of R" + totalCost + ".\n\n"
                            + "Thank you for choosing our hotel. We look forward to welcoming you!\n\n"
                            + "Best regards,\n"
                            + "Hotel Management Team");

            // Send the email
            Transport.send(message);

            return true; // Email sent successfully
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return false; // Failed to send email
        }
    }
}