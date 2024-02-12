package com.bookmyshow.boot.bookmyshow.project.exception;

public class TheatreAdminNotFound extends RuntimeException
{

	private static final long serialVersionUID = 1L;
	String message;
	public String getMessage() 
	{
		return message;
	}
	public TheatreAdminNotFound(String message)
	{
		this.message = message;
	}

}
