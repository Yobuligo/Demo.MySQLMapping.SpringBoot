package com.vpsd.demomysqlmapping;

public class InterestNotFoundException extends ResourceNotFoundException {

	public InterestNotFoundException() {
		super();
	}

	public InterestNotFoundException(Long id) {
		super("interest " + id + " not found");
	}

	public InterestNotFoundException(String message) {
		super(message);
	}

	public InterestNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
