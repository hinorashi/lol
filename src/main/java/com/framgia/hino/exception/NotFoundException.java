package com.framgia.hino.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(Throwable t) {
		super(t);
	}

	public NotFoundException(String m) {
		super(m);
	}
}
