package com.luchoDev.repairmanager.repository;

import com.luchoDev.repairmanager.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    @Query("SELECT t FROM Ticket t WHERE " +
            "t.customerName LIKE %:filter% " +
            "OR t.description LIKE %:filter%")
    public List<Ticket> getFilterTicketList(@Param("filter") String filter);

    List<Ticket> findByStatus(Boolean status);
}
