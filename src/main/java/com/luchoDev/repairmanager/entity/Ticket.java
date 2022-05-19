package com.luchoDev.repairmanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@SQLDelete(sql = "UPDATE ticket SET status = false WHERE id = ?")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate ticketDate;
    private Boolean status;

    public Ticket() {
        this.ticketDate = LocalDate.now();
        this.status=true;
    }
}
