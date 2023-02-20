package com.luchoDev.repairmanager.repository;

import com.luchoDev.repairmanager.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    @Query("SELECT t from Ticket t WHERE t.description LIKE %:value%")
    List<Ticket> findByDescriptionLike(@RequestParam("value") String value);
    @Query("SELECT t from Ticket t " +
            "INNER JOIN Customer c ON t.customer.id = c.id " +
            "WHERE c.name LIKE %:value% OR c.address LIKE  %:value%")
    List<Ticket> findByCustomerLike(@RequestParam("value") String value);

    @Query("SELECT t from Ticket t " +
            "INNER JOIN Customer c ON t.customer.id = c.id " +
            "WHERE c.name LIKE %:value% " +
            "OR c.address LIKE  %:value% OR t.description LIKE %:value%")
    List<Ticket> findByFilter(@RequestParam("value") String value);
}
