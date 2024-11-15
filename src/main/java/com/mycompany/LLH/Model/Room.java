/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LLH.Model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Train
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private int id;
    private int occupants;
    private int floor;
    private int rate;
    private String status;
    private String type; 
    private List<String> images;// Assuming 'type' is the attribute you want to display

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getOccupants() { return occupants; }
    public void setOccupants(int occupants) { this.occupants = occupants; }

    public int getFloor() { return floor; }
    public void setFloor(int floor) { this.floor = floor; }

    public int getRate() { return rate; }
    public void setRate(int rate) { this.rate = rate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public List<String> getImages() {
        return images;
    }
    
    public void setImages(List<String> images) {
        this.images = images;
    }
}


