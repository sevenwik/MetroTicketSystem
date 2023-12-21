package com.metro.metroticketsystem.services;

import com.metro.metroticketsystem.models.Station;
import com.metro.metroticketsystem.models.Ticket;
import com.metro.metroticketsystem.repository.StationRepository;
import com.metro.metroticketsystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class MetroServiceBusinessLogic implements MetroService{
    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket buyTicket(String startStationName, String endStationName) {
        Station startStation = stationRepository.findByName(startStationName);
        Station endStation = stationRepository.findByName(endStationName);

        // Validating stations
        if (startStation == null || endStation == null) {
            throw new IllegalArgumentException("Invalid start or end station");
        }

        // Calculating ticket price
        int ticketPrice = Math.abs(endStation.getPrice() - startStation.getPrice());

        // Creating a new ticket
        Ticket ticket = new Ticket();
        ticket.setStartStation(startStationName);
        ticket.setEndStation(endStationName);
        ticket.setPrice(ticketPrice);
        ticket.setTicketId(generateTicketId());
        ticket.setExpirationTime(LocalDateTime.now().plusHours(18));
        ticket.setUsageCount(0);

        // Saving the ticket to the database
        ticketRepository.save(ticket);

        return ticket;
    }

    @Override
    public boolean enterStation(String ticketId) {
        Optional<Ticket> optionalTicket = ticketRepository.findByTicketId(ticketId);

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();

            // Checking if the ticket is expired or already used twice
            if (ticket.getExpirationTime().isBefore(LocalDateTime.now()) || ticket.getUsageCount() >= 2) {
                return false;
            }

            // Incrementing the usage count and update the ticket
            ticket.setUsageCount(ticket.getUsageCount() + 1);
            ticketRepository.save(ticket);

            return true;
        }

        return false;
    }

    @Override
    public boolean exitStation(String ticketId) {
        Optional<Ticket> optionalTicket = ticketRepository.findByTicketId(ticketId);

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();

            // Checking if the ticket is expired or already used twice
            if (ticket.getExpirationTime().isBefore(LocalDateTime.now()) || ticket.getUsageCount() >= 2) {
                return false;
            }

            // Incrementing the usage count and update the ticket
            ticket.setUsageCount(ticket.getUsageCount() + 1);
            ticketRepository.save(ticket);

            return true;
        }

        return false;
    }

    private String generateTicketId() {
        return UUID.randomUUID().toString();
    }
}
