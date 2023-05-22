package com.example.auto_warehouse.bean;

import jakarta.persistence.Id;

import java.util.Date;

public class order {
    @Id
    public int id;
    @Id
    public int consignorId;
    public String destinationAddress;
    public String departAddress;
    public String status;
    public Date fromDate;
    public Date toDate;
    public Double cost;
    public String currentLocation;
    public String reciverName;
    public String reciverPhone;
}
