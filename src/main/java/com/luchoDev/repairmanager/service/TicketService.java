package com.luchoDev.repairmanager.service;

import com.luchoDev.repairmanager.entity.Customer;
import com.luchoDev.repairmanager.entity.Phone;
import com.luchoDev.repairmanager.entity.Ticket;
import com.luchoDev.repairmanager.repository.CustomerRepository;
import com.luchoDev.repairmanager.repository.PhoneRepository;
import com.luchoDev.repairmanager.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PhoneRepository phoneRepository;
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }
    public Ticket create(Ticket ticket){
        ticket.setCreateDate(new Date());
        return ticketRepository.save(ticket);
    }

    public Ticket update(Ticket dto, Long id) {
        Ticket ticket = ticketRepository.findById(id).get();

        Customer customer = customerRepository.findById(ticket.getCustomer().getId()).get();
        List<Phone> phones = new ArrayList<>();
        customer.getPhones().forEach(x->{
            phones.add(phoneRepository.findById(x.getId()).get());
        });
        List<Phone> phonesDto = dto.getCustomer().getPhones();

        List<Phone> phonesFinal = new ArrayList<>();
        for (Phone x: phones) {
            if(!phonesDto.contains(x)){
                phoneRepository.delete(x);
            }else{
                phonesFinal.add(x);
            }
        }
        for (Phone x: phonesDto) {
            if(!phones.contains(x)){
                phonesFinal.add(x);
            }
        }

        customer.setPhones(phonesFinal);
        customer.setName(dto.getCustomer().getName());
        customer.setAddress(dto.getCustomer().getAddress());
        ticket.setCustomer(customer);
        ticket.setDescription(ticket.getDescription());
        ticket.setUpdateDate(new Date());

        return ticketRepository.save(ticket);
    }
}
