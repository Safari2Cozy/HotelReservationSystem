/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.LLH.Dao;
import com.mycompany.LLH.Model.Payment;
import java.util.List;

public interface PaymentDao {
    boolean addPayment(Payment payment);
    List<Payment> getAllPayments();
}

