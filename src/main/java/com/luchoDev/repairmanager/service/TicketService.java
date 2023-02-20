package com.luchoDev.repairmanager.service;

import com.luchoDev.repairmanager.entity.Customer;
import com.luchoDev.repairmanager.entity.Ticket;
import com.luchoDev.repairmanager.repository.CustomerRepository;
import com.luchoDev.repairmanager.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Ticket findById(Long id) {
        return ticketRepository.findById(id).get();
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket create(Ticket ticket){
        ticket.setCreateDate(LocalDateTime.now());
        ticket.setUpdateDate(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }

    public Ticket update(Ticket dto, Long id) {
        Ticket ticket = ticketRepository.findById(id).get();
        Customer customer = customerRepository.findById(ticket.getCustomer().getId()).get();

        ticket.setCustomer(customer);
        ticket.setDescription(dto.getDescription());
        ticket.setUpdateDate(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    public List<Ticket> findByDescriptionLike(String value){
        return ticketRepository.findByDescriptionLike(value);
    }

    public List<Ticket> findByCustomerLike(String value){
        return ticketRepository.findByCustomerLike(value);
    }

    public List<Ticket> findByFilter(String value) {
        return ticketRepository.findByFilter(value);
    }
}