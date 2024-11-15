/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LLH.ServiceImpl;

import com.mycompany.LLH.Dao.BookingDao;
import com.mycompany.LLH.Model.Booking;
import com.mycompany.LLH.Service.BookingService;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Train
 */
public class BookingServiceImpl implements BookingService {

    private BookingDao bookingDao;

    public BookingServiceImpl(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    @Override
    public boolean addBooking(Booking booking) {
        return bookingDao.addBooking(booking);
    }

    public List<Booking> getBookings(int userId) {
        return bookingDao.getAllBookingsByUserId(userId);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingDao.getAllBookings();
    }

    @Override
    public void updateBookingDates(int id, Timestamp start, Timestamp end) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   @Override
public boolean deleteBooking(int bookingId) {
    return bookingDao.deleteBooking(bookingId);
}


}
