package com.bookmyshow.boot.bookmyshow.project.exception;

public class UserNotFound extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	String message;
	public String getMessage() 
	{
		return message;
	}
	public UserNotFound(String message)
	{
		this.message = message;
	}

}
