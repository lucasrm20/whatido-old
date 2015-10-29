package com.whatido.service;

public class NegocioException extends RuntimeException {
	
	private static final long serialVersionUID = 6637427252354174271L;

	public NegocioException(String msg) {
		super(msg);
	}
}
