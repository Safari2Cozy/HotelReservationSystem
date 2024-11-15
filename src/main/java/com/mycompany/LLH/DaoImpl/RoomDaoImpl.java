/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LLH.DaoImpl;
import com.mycompany.LLH.Dao.RoomDao;
import com.mycompany.LLH.Model.Room;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoomDaoImpl implements RoomDao {

    private Connection con;

    public RoomDaoImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Room getRoom(Room room) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Room fetchedRoom = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
            ps = con.prepareStatement("SELECT * FROM rooms WHERE room_id=?");
            ps.setInt(1, room.getId());
            rs = ps.executeQuery();

            if (rs.next()) {
        fetchedRoom = new Room();
        fetchedRoom.setId(rs.getInt("room_id"));
        fetchedRoom.setOccupants(rs.getInt("occupants"));
        fetchedRoom.setFloor(rs.getInt("floor"));
        fetchedRoom.setRate(rs.getInt("rate"));
        fetchedRoom.setStatus(rs.getString("status"));
        fetchedRoom.setType(rs.getString("room_type"));  // Assuming 'type' column exists in the database
    }

        } catch (SQLException ex) {
            Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fetchedRoom;
    }

    @Override
    public List<Room> getRooms() {
        Statement stmt = null;
        ResultSet rs = null;
        List<Room> rooms = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM rooms");

             while (rs.next()) {
        Room room = new Room();
        room.setId(rs.getInt("room_id"));
        room.setOccupants(rs.getInt("occupants"));
        room.setFloor(rs.getInt("floor"));
        room.setRate(rs.getInt("rate"));
        room.setStatus(rs.getString("status"));
        room.setType(rs.getString("room_type"));  // Assuming 'type' column exists in the database
        rooms.add(room);
    }

        } catch (SQLException ex) {
            Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rooms;
    }

    @Override
    public void resetStatus() {
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
            ps = con.prepareStatement("UPDATE rooms SET status='available' WHERE room_id NOT IN (SELECT room_id FROM bookings WHERE end > ?)");
            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
