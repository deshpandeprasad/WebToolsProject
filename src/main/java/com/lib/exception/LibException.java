package com.lib.exception;

public class LibException extends Exception
{
	public LibException(String message)
	{
		super(message);
	}
	
	public LibException(String message, Throwable cause)
	{
		super(message,cause);
	}
}