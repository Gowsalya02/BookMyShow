package com.bookmyshow.boot.bookmyshow.project.exception;

public class SeatNotFound extends RuntimeException
{

	private static final long serialVersionUID = 4808161474246745072L;
	String message;
	public String getMessage() 
	{
		return message;
	}
	public SeatNotFound(String message)
	{
		this.message = message;
	}

}
