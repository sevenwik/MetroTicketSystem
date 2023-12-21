package com.metro.metroticketsystem.services;

import com.metro.metroticketsystem.models.Ticket;

public interface MetroService {
    Ticket buyTicket(String startStation, String endStation);
    boolean enterStation(String ticketId);
    boolean exitStation(String ticketId);
}
