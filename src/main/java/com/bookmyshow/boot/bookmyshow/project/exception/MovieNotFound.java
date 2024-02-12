package com.bookmyshow.boot.bookmyshow.project.exception;

public class MovieNotFound extends RuntimeException 
{
	private static final long serialVersionUID = 1L;
	String message;
	public String getMessage() 
	{
		return message;
	}
	public MovieNotFound(String message)
	{
		this.message = message;
	}

}
