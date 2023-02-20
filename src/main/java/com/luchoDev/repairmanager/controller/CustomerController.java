package com.luchoDev.repairmanager.controller;

import com.luchoDev.repairmanager.entity.Customer;
import com.luchoDev.repairmanager.entity.Ticket;
import com.luchoDev.repairmanager.repository.CustomerRepository;
import com.luchoDev.repairmanager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(customerService.findById(id),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Customer>> findAll(){
        return new ResponseEntity<>(customerService.findAll(),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@RequestBody Customer dto, @PathVariable("id") Long id){
        return new ResponseEntity<>(customerService.update(dto,id), HttpStatus.OK);
    }
}