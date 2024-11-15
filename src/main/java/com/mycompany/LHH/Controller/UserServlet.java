package com.mycompany.LHH.Controller;

import com.mycompany.LLH.DaoImpl.UserDaoImpl;
import com.mycompany.LLH.LoginException.LoginException;
import com.mycompany.LLH.Model.User;
import com.mycompany.LLH.Service.UserService;
import com.mycompany.LLH.ServiceImpl.UserServiceImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    private final UserService us = new UserServiceImpl(new UserDaoImpl());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        switch(request.getParameter("submit")){
            case "Register":
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                User user = new User();
                user.setEmail(email);
                user.setSurname(surname);
                user.setPassword(password);
                user.setName(name);
                String str = us.register(user);
                if(str.equals("registered successfully")){
                    // Send confirmation email
                    boolean emailSent = sendConfirmationEmail(email);
                    if (emailSent) {
                        response.sendRedirect("Register.jsp?registered=true");
                    } else {
                        response.sendRedirect("Register.jsp?registered=false");
                    }
                }else{
                    request.getRequestDispatcher("index.html").forward(request, response);
                }
                break;
            case "Login":
                try {
                    User u = us.login(request.getParameter("email"), request.getParameter("password"));
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", u);
                    session.setAttribute("userEmail", u.getEmail());
                    response.sendRedirect("Room.jsp");
                } catch (LoginException ex) {
                    request.setAttribute("error", ex.getMessage());
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
                break;
        }
    }

    private boolean sendConfirmationEmail(String userEmail) {
        final String username = "nk.maisela@gmail.com";
        final String password = "nixx ogrx yehj bdek";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username, "Luxury Leisure Hotel"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Registration Confirmation");
            message.setText("Dear Customer,\n\n"
                    + "Your registration was successful! You can now log in using your credentials.\n\n"
                    + "Best regards,\n"
                    + "Luxury Leisure Hotel");

            Transport.send(message);
            return true;

        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
