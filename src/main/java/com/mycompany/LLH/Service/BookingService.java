/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LLH.Service;

import com.mycompany.LLH.Model.Booking;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Train
 */
public interface BookingService {

    boolean addBooking(Booking booking);

    List<Booking> getBookings(int userId);

    List<Booking> getAllBookings();

    public void updateBookingDates(int id, Timestamp start, Timestamp end);

   boolean deleteBooking(int bookingId);
   

}
