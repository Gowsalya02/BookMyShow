package com.bookmyshow.boot.bookmyshow.project.exception;

public class ScreenNotFound extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	String message;
	public String getMessage() 
	{
		return message;
	}
	public ScreenNotFound(String message)
	{
		this.message = message;
	}

}
