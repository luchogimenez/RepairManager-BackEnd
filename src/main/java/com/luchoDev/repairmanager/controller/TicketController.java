package com.luchoDev.repairmanager.controller;

import com.luchoDev.repairmanager.entity.Ticket;
import com.luchoDev.repairmanager.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ticket")
@CrossOrigin("*")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping()
    public ResponseEntity<List<Ticket>> getTicketList(){
        return new ResponseEntity<>(ticketService.getTicketList(), HttpStatus.OK);
    }
}
