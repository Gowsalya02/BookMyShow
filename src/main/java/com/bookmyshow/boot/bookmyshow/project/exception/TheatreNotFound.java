package com.bookmyshow.boot.bookmyshow.project.exception;

public class TheatreNotFound extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	String message;
	public String getMessage() 
	{
		return message;
	}
	public TheatreNotFound(String message)
	{
		this.message = message;
	}

}
