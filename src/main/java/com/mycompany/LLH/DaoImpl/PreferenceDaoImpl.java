/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.LLH.DaoImpl;

import com.mycompany.LLH.Dao.PreferenceDao;
import com.mycompany.LLH.Model.Preference;
import com.mycompany.LLH.Model.Room;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class PreferenceDaoImpl implements PreferenceDao{
    
    private Connection con;
    
    public PreferenceDaoImpl(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        //PreparedStatement ps = con.

    }
    
    @Override
    public List<Preference> getPreferences() {
        
        Statement cs=null;
        ResultSet rs = null;
        List<Preference> preferences = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
            cs = con.createStatement();
            rs = cs.executeQuery("select * from preferences");
              
            while(rs.next()){
                Preference pr = new Preference(rs.getInt("prefer_id"),rs.getString("choice"));
                preferences.add(pr);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(rs!=null){
                    rs.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                if(cs!=null){
                    cs.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }
        
        return preferences;
        
    }
    
}
