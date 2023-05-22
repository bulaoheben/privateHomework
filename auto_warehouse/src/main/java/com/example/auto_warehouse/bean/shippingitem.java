package com.example.auto_warehouse.bean;

import jakarta.persistence.Id;

public class shippingitem {
    @Id
    public int id;

    public String name;
    public int quantity;
    public double weight;
}
