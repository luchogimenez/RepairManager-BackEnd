package com.luchoDev.repairmanager.service;

import com.luchoDev.repairmanager.entity.Ticket;
import com.luchoDev.repairmanager.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getTicketList() {
        return ticketRepository.findAll();
    }
}
