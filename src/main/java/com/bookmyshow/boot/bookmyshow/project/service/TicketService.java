package com.bookmyshow.boot.bookmyshow.project.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.boot.bookmyshow.project.dao.BookingDao;
import com.bookmyshow.boot.bookmyshow.project.dao.SeatDao;
import com.bookmyshow.boot.bookmyshow.project.dao.TicketDao;
import com.bookmyshow.boot.bookmyshow.project.entity.Ticket;
import com.bookmyshow.boot.bookmyshow.project.entity.Booking;
import com.bookmyshow.boot.bookmyshow.project.entity.Seat;
import com.bookmyshow.boot.bookmyshow.project.exception.BookingNotDone;
import com.bookmyshow.boot.bookmyshow.project.exception.TicketNotFound;
import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;

@Service
public class TicketService 
{
	@Autowired
	TicketDao ticketDao;
	
	@Autowired
	SeatDao seatDao;
	
	@Autowired
	BookingDao bookingDao;
	
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
			ticket.setSeatList(null);
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
	
	public ResponseEntity<ResponseStructure<Ticket>> setSeatToTicket(int ticketId,int seatId)
	{
            Ticket ticket=ticketDao.findTicket(ticketId);
		
		if(ticket!=null)
		{
			    List<Seat> seatList=seatDao.findAllSeats();
			    List<Seat>ticketSeat=ticket.getSeatList();
			    if(ticketSeat.isEmpty())
			    {
			    	List<Seat> newSeatList=new ArrayList<Seat>();
			    	newSeatList.add(seatDao.findSeat(seatId));
			    	ticket.setSeatList(newSeatList);	
			    }
			    else 
			    {
			    	for (Seat seat : seatList) 
			    	{
			    		if(seat.getSeatId()==seatId)
			    		{
			    			ticketSeat.add(seatDao.findSeat(seatId));
			    			ticket.setSeatList(ticketSeat);
			    		}
						
					}
			    }
			    ResponseStructure<Ticket> structure=new ResponseStructure<Ticket>();
			    structure.setMessage("Seat list assign to ticket");
			    structure.setStatus(HttpStatus.OK.value());
			    structure.setData(ticketDao.updateTicket(ticket,ticket.getTicketNumber()));
			    
          return new ResponseEntity<ResponseStructure<Ticket>>(structure,HttpStatus.OK);
		}
		throw new TicketNotFound("Ticket object is not found for the given ");
		
	}
	
	public ResponseEntity<ResponseStructure<Ticket>> removeSeatFromTicket(int ticketId,int seatId)
	{
		Ticket ticket=ticketDao.findTicket(ticketId);
		if(ticket!=null)
		{
			List<Seat>ticketSeat=ticket.getSeatList();
			
			if(ticketSeat!=null)
			{
			for (Seat seat : ticketSeat) 
			{
				if(seat.getSeatId()==seatId)
				{
					ticketSeat.remove(seat);
				}
			}
			
			ticket.setSeatList(ticketSeat);
			}
			else
			{
				List<Seat>newSeatList=new ArrayList<Seat>();
				newSeatList.add(seatDao.findSeat(seatId));
				ticket.setSeatList(newSeatList);
			}
			ResponseStructure<Ticket> structure=new ResponseStructure<Ticket>();
			structure.setMessage("seat removed");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(ticketDao.updateTicket(ticket, ticketId));
		return new ResponseEntity<ResponseStructure<Ticket>>(structure,HttpStatus.OK);
		}
		throw new TicketNotFound("ticket object not found for the given id");
	}

	public ResponseEntity<ResponseStructure<Ticket>> setBookingToTicket(int ticketId,int bookingId)
	{
		Ticket ticket=ticketDao.findTicket(ticketId);
		if(ticket!=null)
		{
			Booking booking= bookingDao.findBooking(bookingId);
			if(booking!=null)
			{
				if(ticket.getBooking().equals(null))
				{
					ticket.setBooking(booking);
					ResponseStructure<Ticket>structure=new ResponseStructure<Ticket>();
					structure.setMessage("booking is done for the ticket");
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(ticketDao.updateTicket(ticket, ticketId));
					return new ResponseEntity<ResponseStructure<Ticket>>(structure,HttpStatus.OK);
					
				}
				throw new TicketNotFound("this ticket already booked");
			}
			throw new BookingNotDone("booking is not found for the given Id");
		}
		throw new TicketNotFound("ticket object not found for the given id");
	}
	
}
