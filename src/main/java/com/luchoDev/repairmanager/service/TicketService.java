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
    public List<Ticket> getTicketListByStatusTrue() {
        return ticketRepository.findByStatus(true);
    }

    public List<Ticket> getFilterTicketList(String filter) {
        return ticketRepository.getFilterTicketList(filter);
    }

    public void deleteTicketById(Long id) {
        ticketRepository.deleteById(id);
    }

    public Ticket createTicket(Ticket ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setCustomerName(ticketDTO.getCustomerName().toUpperCase());
        ticket.setCustomerAddress(ticketDTO.getCustomerAddress().toUpperCase());
        ticket.setCustomerPhone(ticketDTO.getCustomerPhone().toUpperCase());
        ticket.setDescription(ticketDTO.getDescription().toUpperCase());
        return ticketRepository.save(ticket);
    }

    public Ticket getTicketById(Long id) throws Exception {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(()->new Exception("No se encontró ticket con ese id: "+id));
        return ticket;
    }

    public Ticket updateTicket(Ticket ticketDTO, Long id) throws Exception {
        Ticket ticket = getTicketById(id);
        ticket.setCustomerName(ticketDTO.getCustomerName()!=null?ticketDTO.getCustomerName().toUpperCase():ticket.getCustomerName());
        ticket.setCustomerAddress(ticketDTO.getCustomerAddress()!=null?ticketDTO.getCustomerAddress().toUpperCase():ticket.getCustomerAddress());
        ticket.setCustomerPhone(ticketDTO.getCustomerPhone()!=null?ticketDTO.getCustomerPhone().toUpperCase():ticket.getCustomerPhone());
        ticket.setDescription(ticketDTO.getDescription()!=null?ticketDTO.getDescription().toUpperCase():ticket.getDescription());
        return ticketRepository.save(ticket);
    }
}
