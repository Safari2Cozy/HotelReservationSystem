package com.mycompany.LLH.ServiceImpl;

import com.mycompany.LLH.Dao.AdminDao;
import com.mycompany.LLH.DaoImpl.AdminNotFoundException;
;
import com.mycompany.LLH.Model.Admin;
import com.mycompany.LLH.Service.AdminService;
import com.mycompany.LLH.LoginException.LoginException;
import com.mycompany.LLH.Model.Booking;
import java.sql.SQLException;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */


public class AdminServiceImpl implements AdminService {

    private AdminDao adminDao;

    public AdminServiceImpl(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public Admin login(String email, String password) throws AdminNotFoundException, LoginException {
      
            Admin admin = adminDao.getAdminByEmail(email);
           
            if (!BCrypt.checkpw(password,admin.getPassword())) {
                throw new LoginException("password incorrect");
            }
            return admin;
    }

    @Override
    public List<Booking> getAllBookings() {
        return adminDao.getAllBookings();
    }

    @Override
    public void updateBooking(Booking booking) throws SQLException {
        adminDao.updateBooking(booking);
    }
}
