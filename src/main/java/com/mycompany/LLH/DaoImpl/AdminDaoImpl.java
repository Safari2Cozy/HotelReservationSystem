 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.LLH.DaoImpl;

import com.mycompany.LLH.Dao.AdminDao;
import com.mycompany.LLH.Model.Admin;
import com.mycompany.LLH.LoginException.LoginException;
import com.mycompany.LLH.Model.Booking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class AdminDaoImpl implements AdminDao{
    
    private Connection con;
    
    public AdminDaoImpl(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        //PreparedStatement ps = con.

    }

    @Override
    public Admin getAdminByEmail(String email) throws AdminNotFoundException {
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("select * from admin where email=?");
            ps.setString(1, email);
            rs = ps.executeQuery();
              
            if(rs.next()){
               Admin admin1 = new Admin();
                admin1.setId(rs.getInt("id"));
                admin1.setName(rs.getString("name"));
                admin1.setSurname(rs.getString("surname"));
                admin1.setPassword(rs.getString("password"));
                admin1.setEmail(rs.getString("email"));
                return admin1;
            }
            throw new AdminNotFoundException("No such admin...");

        } catch (SQLException ex) {
                throw new RuntimeException("Internal server error");
        }finally{
            
            try {
                if(rs!=null){
                    rs.close();                    
                }

            } catch (SQLException ex) {
                throw new RuntimeException("Couldn't close connection");

            }
            
        }
    

    }
    
     @Override
public List<Booking> getAllBookings() {
    List<Booking> bookings = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
        ps = con.prepareStatement("SELECT * FROM bookings");
        rs = ps.executeQuery();

        while (rs.next()) {
            Booking booking = new Booking();
            booking.setId(rs.getInt("id"));
            booking.setRoom(rs.getString("room"));
            booking.setStart(rs.getTimestamp("start"));
            booking.setEnd(rs.getTimestamp("end"));
            booking.setUserId(rs.getInt("user_id"));
            bookings.add(booking);
        }
    } catch (SQLException ex) {
        Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    return bookings;
}


    @Override
public void updateBooking(Booking booking) throws SQLException {
    PreparedStatement ps = null;

    try {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
        ps = con.prepareStatement("UPDATE bookings SET room=?, start=?, end=?, user_id=? WHERE id=?");
        ps.setString(1, booking.getRoom());
        ps.setTimestamp(2, booking.getStart());
        ps.setTimestamp(3, booking.getEnd());
        ps.setInt(4, booking.getUserId());
        ps.setInt(5, booking.getId());
        ps.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        throw ex;
    } finally {
        try {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
}

    
