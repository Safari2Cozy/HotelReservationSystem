/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LLH.ServiceImpl;

import com.mycompany.LLH.Service.UserService;
import com.mycompany.LLH.Model.Email;
import com.mycompany.LLH.LoginException.LoginException;
import com.mycompany.LLH.Dao.UserDao;
import com.mycompany.LLH.Model.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Train
 */
public class UserServiceImpl implements UserService{
    
    private UserDao userDao;
    
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }
            
    @Override
    public String register(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);

        String str = userDao.addUser(user)?"registered successfully":"failed to register";  // make addUSer return boolean
//        if(str.equals("registered successfully")){ 
//            Email mail = new Email(user.getEmail(),user.getEmail(),str,"vhya lebl moop qksf","registration");
//            EmailServiceImpl es = new EmailServiceImpl(mail);
//            es.sendMail();
//        }
        return str;
    }

    @Override
    public User login(String email,String password) throws LoginException{
        User user1 = null;
        String hash="";
        try {
            user1 = userDao.getUserByEmail(email);
            hash = user1.getPassword();
        } catch (LoginException ex) {
            throw new LoginException();
        }
               
        if(!BCrypt.checkpw(password, hash)){
            throw new LoginException("password incorrect");
        }
        return user1;
    }
    
}
