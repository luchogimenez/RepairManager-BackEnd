package com.luchoDev.repairmanager.controller;

import com.luchoDev.repairmanager.entity.Ticket;
import com.luchoDev.repairmanager.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ticket")
@CrossOrigin("*")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping()
    public ResponseEntity<List<Ticket>> getTicketList(){
        return new ResponseEntity<>(ticketService.findAll(), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<Ticket> create(@RequestBody Ticket ticket){
        return new ResponseEntity<>(ticketService.create(ticket), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> update(@RequestBody Ticket ticket,@PathVariable("id") Long id){
        return new ResponseEntity<>(ticketService.update(ticket,id), HttpStatus.OK);
    }
}
