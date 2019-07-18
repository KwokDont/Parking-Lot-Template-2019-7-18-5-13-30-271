package com.thoughtworks.parking_lot.model;

import javax.persistence.*;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "lot_name",nullable = false)
    private String lotName;

    @Column(name = "car_no",nullable = false)
    private String carNo;

    @Column(name = "create_time",nullable = false)
    private long createTime;

    @Column(name = "end_time")
    private long endTime;

    @Column(name = "status",nullable = false)
    private String status;

    public Order() {
    }

    public Order(String lotName, String carNo, long createTime, String status) {
        this.lotName = lotName;
        this.carNo = carNo;
        this.createTime = createTime;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
