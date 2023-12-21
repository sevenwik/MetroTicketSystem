package com.metro.metroticketsystem.controllers;

import com.metro.metroticketsystem.models.Ticket;
import com.metro.metroticketsystem.services.MetroService;
import com.metro.metroticketsystem.services.MetroServiceBusinessLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/metro")
public class MetroTicketController {
    @Autowired
    private MetroServiceBusinessLogic metroService;
    @PostMapping("/buyTicket")
    public ResponseEntity<Ticket> buyTicket(@RequestParam String startStation, @RequestParam String endStation) {
        try {
            Ticket ticket = metroService.buyTicket(startStation, endStation);
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
