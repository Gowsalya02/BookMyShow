package com.bookmyshow.boot.bookmyshow.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmyshow.boot.bookmyshow.project.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> 
{

}
