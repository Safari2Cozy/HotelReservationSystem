/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LLH.ServiceImpl;

import com.mycompany.LLH.Service.EmailService;
import com.mycompany.LLH.Model.Email;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


/**
 *
 * @author Train
 */
public class EmailServiceImpl implements EmailService{

    private Email email;
    
    public EmailServiceImpl(Email email){
        this.email = email;
    }
    
    public void sendMail(){
        
        final String USERNAME = email.getSender();
        final String PASSWORD = email.getPassword();
        String reciever = email.getReciever();
        String subject = email.getSubject();
        String content = email.getMessage();

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");   

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(reciever));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
}
