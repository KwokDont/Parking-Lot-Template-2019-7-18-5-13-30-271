package com.thoughtworks.parking_lot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ParkingLot {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "capacity",nullable = false)
    private int capacity;

    @Column(name = "location",nullable = false)
    private String location;

    public ParkingLot() {
    }

    public ParkingLot(String name, int capacity, String location) {
        this.name = name;
        this.capacity = capacity;
        this.location = location;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getCapacity() { return capacity; }

    public void setCapacity(int capacity) { this.capacity = capacity; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }
}
