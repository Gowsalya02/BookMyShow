package com.bookmyshow.boot.bookmyshow.project.exception;

public class ReviewNotFound extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	String message;
	public String getMessage() 
	{
		return message;
	}
	public ReviewNotFound(String message)
	{
		this.message = message;
	}

}
