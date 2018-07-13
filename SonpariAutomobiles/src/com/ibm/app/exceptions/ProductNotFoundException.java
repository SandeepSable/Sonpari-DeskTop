package com.ibm.app.exceptions;

public class ProductNotFoundException extends Exception
{
	private static final long serialVersionUID = 8272988390493578350L;

	public ProductNotFoundException(String message)
	{
		super(message);
	}
}
