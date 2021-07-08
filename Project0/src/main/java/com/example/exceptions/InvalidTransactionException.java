package com.example.exceptions;

public class InvalidTransactionException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidTransactionException() {
		super("Invalid Transaction attempted, Money can NOT be negative and accounts Cannot be below $0");
	}

}
