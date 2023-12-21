package com.metro.metroticketsystem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean startStation;
    private boolean lastStation;
    private int price;

    public String getName(){
        return name;
    }
    public boolean getStartStation(){
        return startStation;
    }
    public boolean getLastStation(){
        return lastStation;
    }
    public int getPrice(){
        return price;
    }
}
