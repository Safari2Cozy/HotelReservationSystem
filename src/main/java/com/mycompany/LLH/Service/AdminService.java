/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.LLH.Service;

import com.mycompany.LLH.DaoImpl.AdminNotFoundException;
import com.mycompany.LLH.Model.Admin;
import com.mycompany.LLH.LoginException.LoginException;
import com.mycompany.LLH.Model.Booking;
import java.sql.SQLException;
import java.util.List;

public interface AdminService {
    Admin login(String email,String password) throws AdminNotFoundException,LoginException;
    List<Booking> getAllBookings();
    void updateBooking(Booking booking) throws SQLException;
}
