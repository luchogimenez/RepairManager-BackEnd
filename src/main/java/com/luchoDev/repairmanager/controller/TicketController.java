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

    @GetMapping("")
    public ResponseEntity<List<Ticket>> getTicketList(){
        return new ResponseEntity<>(ticketService.getTicketList(), HttpStatus.OK);
    }
    @GetMapping("/status-true")
    public ResponseEntity<List<Ticket>> getTicketListByStatusTrue(){
        return new ResponseEntity<>(ticketService.getTicketListByStatusTrue(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(ticketService.getTicketById(id), HttpStatus.OK);
    }

    @GetMapping("/search-ticket-list-with-filter")
    public ResponseEntity<List<Ticket>> getFilterTicketList(@RequestParam("filter") String filter){
        return new ResponseEntity<>(ticketService.getFilterTicketList(filter.toUpperCase()), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket){
        return new ResponseEntity<Ticket>(ticketService.createTicket(ticket), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket,@PathVariable Long id) throws Exception {
        return new ResponseEntity<Ticket>(ticketService.updateTicket(ticket,id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteTicketById(@PathVariable("id") Long id){
         ticketService.deleteTicketById(id);
    }
}
