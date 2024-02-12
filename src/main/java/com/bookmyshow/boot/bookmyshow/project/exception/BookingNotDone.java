package com.bookmyshow.boot.bookmyshow.project.exception;
public class BookingNotDone extends RuntimeException 
{
	private static final long serialVersionUID = 1L;
	String message;
	public String getMessage() 
	{
		return message;
	}
	public BookingNotDone(String message)
	{
		this.message = message;
	}

}
