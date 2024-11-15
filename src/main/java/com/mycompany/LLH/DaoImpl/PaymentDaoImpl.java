/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.LLH.DaoImpl;
import com.mycompany.LLH.Dao.PaymentDao;
import com.mycompany.LLH.Model.Payment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {

    private static final String INSERT_PAYMENT_SQL = "INSERT INTO payment (room_type, number_of_nights, total_cost) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_PAYMENTS_SQL = "SELECT * FROM payment";

    @Override
    public boolean addPayment(Payment payment) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PAYMENT_SQL)) {
            preparedStatement.setString(1, payment.getRoomType());
            preparedStatement.setInt(2, payment.getNumberOfNights());
            preparedStatement.setDouble(3, payment.getTotalCost());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM payment";
        
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PAYMENTS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int paymentId = resultSet.getInt("payment_id");
                String roomType = resultSet.getString("room_type");
                int numberOfNights = resultSet.getInt("number_of_nights");
                double totalCost = resultSet.getDouble("total_cost");
                Payment payment = new Payment(paymentId, roomType, numberOfNights, totalCost);
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }
}


