/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LLH.Service;

import com.mycompany.LLH.LoginException.LoginException;
import com.mycompany.LLH.Model.User;

/**
 *
 * @author Train
 */
public interface UserService {
    
    String register(User user);
    User login(String email,String password) throws LoginException;
    
}
