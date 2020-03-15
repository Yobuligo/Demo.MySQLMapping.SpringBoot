package com.vpsd.demomysqlmapping;

public class PersonNotFoundException extends ResourceNotFoundException {

	public PersonNotFoundException() {
		super();
	}

	public PersonNotFoundException(Long id) {
		super("person " + id + " not found");
	}

	public PersonNotFoundException(String message) {
		super(message);
	}

	public PersonNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}

}