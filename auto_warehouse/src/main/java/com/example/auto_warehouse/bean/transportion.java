package com.example.auto_warehouse.bean;

import jakarta.persistence.Id;

import java.util.Date;

public class transportion {
    @Id
    public int id;
    @Id
    public int tranId;

    public String  destinationAddress;
    public String  departAddress;
    public String currentLocation;
    public Date fromDate;
}
