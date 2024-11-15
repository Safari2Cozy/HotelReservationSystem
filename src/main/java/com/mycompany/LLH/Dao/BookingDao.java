/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LLH.Dao;

import com.mycompany.LLH.Model.Booking;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Train
 */
public interface BookingDao {
    
    boolean addBooking(Booking booking);
    List<Booking> getAllBookings();  // get all of that user
    List<Booking> getAllBookingsByUserId(int userId);
    boolean confirmBooking(int bookingId);
    boolean updateBookingDates(int bookingId, Timestamp start, Timestamp end);
    boolean deleteBooking(int bookingId);

}


