/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.LLH.DaoImpl;

/**
 *
 * @author Train
 */
public class AdminNotFoundException extends Exception {

    public AdminNotFoundException(String no_such_admin) {
        super(no_such_admin);
    }
    
}
