package com.tarrant.sms.exception;

/**
 * Runtime Exception to wrap provider-specific exceptions
 * 
 * @author declantarrant
 */
public class SmsException extends RuntimeException {

	private static final long serialVersionUID = 2944964298413550916L;

	public SmsException(String message) {
		super(message);
	}
	
}
