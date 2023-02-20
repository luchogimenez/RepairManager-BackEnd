package com.luchoDev.repairmanager.service;

import com.luchoDev.repairmanager.entity.Customer;
import com.luchoDev.repairmanager.entity.Phone;
import com.luchoDev.repairmanager.repository.CustomerRepository;
import com.luchoDev.repairmanager.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PhoneRepository phoneRepository;
    public Customer findById(Long id){
        return customerRepository.findById(id).get();
    }
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
    public Customer update(Customer dto,Long id){
        Customer customer = customerRepository.findById(id).get();

        List<Phone> phones = new ArrayList<>();
        customer.getPhones().forEach(x->{
            phones.add(phoneRepository.findById(x.getId()).get());
        });

        List<Phone> phonesDto = dto.getPhones();
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
        customer.setName(dto.getName());
        customer.setCity(dto.getCity());
        customer.setAddress(dto.getAddress());

        return customerRepository.save(customer);
    }
}