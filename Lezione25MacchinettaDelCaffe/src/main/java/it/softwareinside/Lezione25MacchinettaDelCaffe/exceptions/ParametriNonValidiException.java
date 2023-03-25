package it.softwareinside.Lezione25MacchinettaDelCaffe.exceptions;

public class ParametriNonValidiException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ParametriNonValidiException() {
		super();
	}
	public ParametriNonValidiException(String arg0) {
		super(arg0);
	}
}