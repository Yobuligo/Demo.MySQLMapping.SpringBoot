package com.vpsd.demomysqlmapping;

public class BuddyNotFoundException extends ResourceNotFoundException {

	public BuddyNotFoundException() {
		super();
	}

	public BuddyNotFoundException(Long id) {
		super("buddy " + id + " not found");
	}

	public BuddyNotFoundException(String message) {
		super(message);
	}

	public BuddyNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
