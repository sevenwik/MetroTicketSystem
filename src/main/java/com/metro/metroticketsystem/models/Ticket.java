package com.metro.metroticketsystem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.aspectj.apache.bcel.classfile.Module;

import java.time.LocalDateTime;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String startStation;
    private String endStation;
    private int price;
    private String ticketId;
    private LocalDateTime expirationTime;
    private int usageCount;

    public String getStartStation(){
        return startStation;
    }
    public String getEndStation(){
        return endStation;
    }
    public int getPrice(){
        return price;
    }
    public String getTicketId(){
        return ticketId;
    }
    public LocalDateTime getExpirationTime(){
        return expirationTime;
    }


    public void setStartStation(String startStationName) {
        this.startStation  = startStationName;
    }

    public void setEndStation(String endStationName) {
        this.endStation = endStationName;
    }

    public void setPrice(int ticketPrice) {
        this.price = ticketPrice;
    }

    public void setUsageCount(int i) {
        this.usageCount = i;
    }

    public void setExpirationTime(LocalDateTime localDateTime) {
        this.expirationTime = localDateTime;
    }

    public void setTicketId(String ticketId){
        this.ticketId = ticketId;
    }

    public int getUsageCount() {
        return usageCount;
    }
}
