/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.LLH.LoginException;

/**
 *
 * @author User
 */
public class LoginException extends Exception{
    
    public LoginException(String message){
        super(message);
    }
    
    public LoginException(){
        this("username or password incorrect");
    }
}
