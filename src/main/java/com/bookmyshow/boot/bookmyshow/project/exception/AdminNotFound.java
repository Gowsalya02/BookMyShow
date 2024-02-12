package com.bookmyshow.boot.bookmyshow.project.exception;

public class AdminNotFound extends RuntimeException
{
	private static final long serialVersionUID = 1624501271557284788L;
	String message;
	public String getMessage() 
	{
		return message;
	}
	public AdminNotFound(String message)
	{
		this.message = message;
	}
}
