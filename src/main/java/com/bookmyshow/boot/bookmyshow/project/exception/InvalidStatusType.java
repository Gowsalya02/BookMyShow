package com.bookmyshow.boot.bookmyshow.project.exception;

public class InvalidStatusType extends RuntimeException 
{
	private static final long serialVersionUID = -5709606652346499447L;
	String message;
	public InvalidStatusType(String message) {
		super();
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	
	

	
}
