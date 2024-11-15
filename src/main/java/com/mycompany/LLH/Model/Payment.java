/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.LLH.Model;



public class Payment {
    private int paymentId;
    private String roomType;
    private int numberOfNights;
    private double totalCost;

    // Default constructor
    public Payment() {
    }

    // Constructor with all fields
    public Payment(int paymentId, String roomType, int numberOfNights, double totalCost) {
        this.paymentId = paymentId;
        this.roomType = roomType;
        this.numberOfNights = numberOfNights;
        this.totalCost = totalCost;
    }

    // Constructor without paymentId
    public Payment(String roomType, int numberOfNights, double totalCost) {
        this.roomType = roomType;
        this.numberOfNights = numberOfNights;
        this.totalCost = totalCost;
    }

    // Getters and setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}

