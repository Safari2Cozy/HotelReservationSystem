package com.mycompany.LLH.DaoImpl;

import com.mycompany.LLH.Dao.BookingDao;
import com.mycompany.LLH.Model.Booking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingDaoImpl implements BookingDao {

    private Connection con;

    public BookingDaoImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to establish a database connection
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false";
        String user = "root";
        String password = "root";
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public boolean addBooking(Booking booking) {
        PreparedStatement ps = null;
        boolean success = false;
        try {
            con = getConnection();
            ps = con.prepareStatement("INSERT INTO bookings (room, start, end, user_id) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, booking.getRoom());
            ps.setTimestamp(2, booking.getStart());
            ps.setTimestamp(3, booking.getEnd());
            ps.setInt(4, booking.getUserId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        booking.setId(generatedKeys.getInt(1));
                        success = true;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return success;
    }

    @Override
    public List<Booking> getAllBookingsByUserId(int userId) {
        List<Booking> bookings = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM bookings WHERE user_id = ?");
            ps.setInt(1, userId);
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
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bookings;
    }

    @Override
    public boolean confirmBooking(int bookingId) {
        boolean success = false;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("UPDATE bookings SET confirmed = true WHERE id = ?");
            ps.setInt(1, bookingId);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return success;
    }

    @Override
    public boolean updateBookingDates(int bookingId, Timestamp start, Timestamp end) {
        boolean success = false;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("UPDATE bookings SET start = ?, end = ? WHERE id = ?");
            ps.setTimestamp(1, start);
            ps.setTimestamp(2, end);
            ps.setInt(3, bookingId);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return success;
    }



    @Override
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM bookings");
            rs = ps.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("booking_id")); // Corrected column name
                booking.setRoom(rs.getString("room"));
                booking.setStart(rs.getTimestamp("start"));
                booking.setEnd(rs.getTimestamp("end"));
                booking.setUserId(rs.getInt("user_id"));
                bookings.add(booking);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bookings;
    }
    @Override
public boolean deleteBooking(int bookingId) {
     boolean success = false;
    PreparedStatement ps = null;
    try {
        con = getConnection();
        ps = con.prepareStatement("DELETE FROM bookings WHERE booking_id = ?");
        ps.setInt(1, bookingId);

        int rowsDeleted = ps.executeUpdate();
        if (rowsDeleted > 0) {
            success = true;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    return success;
}




}
