package com.demo.exception;

public class UserException extends RuntimeException {

	private String message;

	public UserException(String message) {
		super();
		this.message = message;
	}

	public UserException() {
		super();
		this.message = "User Exception";
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
