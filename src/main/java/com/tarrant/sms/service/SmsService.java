package com.tarrant.sms.service;

import com.tarrant.sms.exception.SmsException;
import com.tarrant.sms.model.SmsMessage;

/**
 * Service interface for SMS provider
 * 
 * @author declantarrant
 */
public interface SmsService {
	
	/**
	 * Sends an SMS message to the chosen recipient
	 * @param to
	 * @param message
	 * @return
	 * @throws SmsException
	 */
	public String sendSMS(String to, String message) throws SmsException;
	
	/**
	 * Retrieve the status of the SMS message from Twilio
	 * @param msgCid
	 * @return
	 * @throws SmsException
	 */
	public SmsMessage retrieveSentSMS(String msgCid) throws SmsException;
	
}
