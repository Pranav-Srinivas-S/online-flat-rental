package com.capg.onlineflatrental.exception;

public class LandlordNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public LandlordNotFoundException(String message)
	{
		super(message);
	}
}
