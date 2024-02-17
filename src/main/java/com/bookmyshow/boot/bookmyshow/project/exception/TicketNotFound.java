package com.bookmyshow.boot.bookmyshow.project.exception;

public class TicketNotFound  extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3037536723255403212L;
	String message;
	public String getMessage() 
	{
		return message;
	}
	public TicketNotFound(String message)
	{
		this.message = message;
	}
}
