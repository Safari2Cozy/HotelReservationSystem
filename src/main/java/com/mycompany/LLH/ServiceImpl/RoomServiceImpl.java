/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LLH.ServiceImpl;

import com.mycompany.LLH.Service.RoomService;
import com.mycompany.LLH.Dao.RoomDao;
import com.mycompany.LLH.Model.Room;
import java.util.List;

/**
 *
 * @author Train
 */
public class RoomServiceImpl implements RoomService {

    private RoomDao roomDao;

    // Constructor that ensures RoomDao is not null
    public RoomServiceImpl(RoomDao roomDao) {
        if (roomDao == null) {
            throw new IllegalArgumentException("RoomDao cannot be null");
        }
        this.roomDao = roomDao;
    }

    @Override
    public Room getRoom(Room room) {
        return roomDao.getRoom(room);
    }

    @Override
    public List<Room> getRooms() {
        roomDao.resetStatus();
        return roomDao.getRooms();
    }
}


