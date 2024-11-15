/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LLH.Dao;

import com.mycompany.LLH.Model.Room;
import java.util.List;

/**
 *
 * @author Train
 */
public interface RoomDao {
    
    Room getRoom(Room room);
    List<Room> getRooms();
    void resetStatus();
    
}
