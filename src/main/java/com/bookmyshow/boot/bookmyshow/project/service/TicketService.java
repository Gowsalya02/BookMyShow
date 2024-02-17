package com.bookmyshow.boot.bookmyshow.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.boot.bookmyshow.project.dao.TicketDao;
import com.bookmyshow.boot.bookmyshow.project.entity.Ticket;
import com.bookmyshow.boot.bookmyshow.project.exception.TicketNotFound;
import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;

@Service
public class TicketService 
{
	@Autowired
	TicketDao ticketDao;
	
	public ResponseEntity<ResponseStructure<Ticket>> saveTicket(Ticket ticket)
	{
		ResponseStructure<Ticket> structure=new ResponseStructure<Ticket>();
		structure.setMessage(" ticket object saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(ticketDao.saveTicket(ticket));
		return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Ticket>> findTicket(int ticketId)
	{
		Ticket ticket=ticketDao.findTicket(ticketId);
		if(ticket!=null)
		{
	    ResponseStructure<Ticket> structure=new ResponseStructure<Ticket>();
	    structure.setMessage("Found ticket");
	    structure.setStatus(HttpStatus.FOUND.value());
	    structure.setData(ticket);
		return  new ResponseEntity<ResponseStructure<Ticket>>(structure,HttpStatus.FOUND);
		}
		throw new TicketNotFound("ticket object not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Ticket>> deleteTicket(int ticketId)
	{
		Ticket ticket =ticketDao.findTicket(ticketId);
		if(ticket!=null)
		{
			ticketDao.deleteTicket(ticketId);
			ResponseStructure<Ticket> structure=new ResponseStructure<Ticket>();
			structure.setMessage("ticket deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(ticket);
		return new ResponseEntity<ResponseStructure<Ticket>>(structure,HttpStatus.OK);
		}
		throw new TicketNotFound("ticket object not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Ticket>> updateTicket(Ticket ticket,int ticketId)
	{
		Ticket exticket=ticketDao.findTicket(ticketId);
		if(exticket!=null) 
		{
		ResponseStructure<Ticket> structure=new ResponseStructure<Ticket>();
		structure.setMessage("ticket Updated");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(ticketDao.updateTicket(ticket, ticketId));
		return new ResponseEntity<ResponseStructure<Ticket>>(structure,HttpStatus.OK);
		}
		throw new TicketNotFound("ticket object not found for the given id");
	}
	
	public List<Ticket> findAllTickets()
	{
		List<Ticket> ticketList=ticketDao.findAllTickets();
		if(ticketList!=null) 
		{
		ResponseStructure<List<Ticket>> structure= new ResponseStructure<List<Ticket>>();
		structure.setMessage("list of all ticket");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(ticketList);
		return ticketDao.findAllTickets();
		}
		return null;//no tickets available
	}

}
