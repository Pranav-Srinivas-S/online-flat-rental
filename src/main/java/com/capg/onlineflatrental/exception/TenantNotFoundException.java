package com.capg.onlineflatrental.exception;

public class TenantNotFoundException  extends Exception{

	private static final long serialVersionUID = 1L;
	
	public TenantNotFoundException(String message)
	{
		super(message);
	}
}
