/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.LLH.ServiceImpl;

import com.mycompany.LLH.Model.Payment;
import com.mycompany.LLH.Dao.PaymentDao;
import com.mycompany.LLH.DaoImpl.PaymentDaoImpl;
import com.mycompany.LLH.DaoImpl.PaymentDaoImpl;
import com.mycompany.LLH.Service.PaymentService;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    private final PaymentDao paymentDao = new PaymentDaoImpl();

    @Override
    public boolean addPayment(Payment payment) {
        return paymentDao.addPayment(payment);
    }

    @Override
    public List<Payment> getAllPayments() { // Implement this method
        return paymentDao.getAllPayments();
    }
}
