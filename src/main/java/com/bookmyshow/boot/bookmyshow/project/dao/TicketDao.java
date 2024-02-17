package com.bookmyshow.boot.bookmyshow.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.boot.bookmyshow.project.entity.Ticket;
import com.bookmyshow.boot.bookmyshow.project.repository.TicketRepository;

@Repository
public class TicketDao 
{
	@Autowired
	TicketRepository ticketRepository;
	
	public Ticket saveTicket(Ticket ticket)
	{
		return ticketRepository.save(ticket);
	}
	
	public Ticket findTicket(int ticketId)
	{
		Optional<Ticket> optional= ticketRepository.findById(ticketId);
		
		if(optional.isPresent())
		{
			return optional.get();
		}
		return null;//ticket object not present
	}
	
	public Ticket deleteTicket(int ticketId)
	{
		Ticket exTicket=findTicket(ticketId);
		if(exTicket!=null)
		{
			ticketRepository.deleteById(ticketId);
		return exTicket;
		}
	
		return null;//ticket object is found for the given ID
	}

	public Ticket updateTicket(Ticket ticket,int ticketId)
	{
		Ticket exTicket=findTicket(ticketId);
		
		if(exTicket!=null)
		{
			ticket.setTicketNumber(ticketId);
			return ticketRepository.save(ticket);
		}
		return null;//ticket object is not found for the given ID
	}
	
	public List<Ticket> findAllTickets()
	{
		return ticketRepository.findAll();
	}


}
