package com.ibm.app.exceptions;

import org.springframework.stereotype.Component;

@Component
public class ProductException extends Exception
{
	private static final long serialVersionUID = 8676672254865245646L;

	public ProductException() 
	{
	}
	public ProductException(String str) 
	{
      super(str);
	}
}
