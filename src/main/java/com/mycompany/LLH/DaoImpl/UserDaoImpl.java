package com.mycompany.LLH.DaoImpl;

import com.mycompany.LLH.Dao.UserDao;
import com.mycompany.LLH.LoginException.LoginException;
import com.mycompany.LLH.Model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Train
 */
public class UserDaoImpl implements UserDao {

    //String url = "jdbc:mysql://localhost:3306/hotel?useSSL=false";
    private Connection con;
    private PreparedStatement ps;

    public UserDaoImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded...");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?useSSL=false", "root", "root");
            System.out.println("connected!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public boolean addUser(User user) {
        int affect = 0;
        
        System.out.println(user);
      
        try {

            ps = con.prepareStatement("insert into users (name,surname,email,password) values(?,?,?,?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());

            affect = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
            ex.printStackTrace();
            }
         
        }
        return affect == 1;
    }

    @Override
    public User getUserByEmail(String email) throws LoginException {

        ResultSet rs = null;
        User user1 = null;
        try {
            ps = con.prepareStatement("select * from users where email=?");
            ps.setString(1, email);
            rs = ps.executeQuery();

            while (rs.next()) {
                user1 = new User();
                user1.setId(rs.getInt("id"));
                user1.setName(rs.getString("name"));
                user1.setSurname(rs.getString("surname"));
                user1.setPassword(rs.getString("password"));
                user1.setEmail(rs.getString("email"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException ex) {
            ex.printStackTrace();
            }

        

        }

        if (user1 == null) {
            throw new LoginException();
        }

        return user1;
    }

}
