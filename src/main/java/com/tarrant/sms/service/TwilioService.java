package com.tarrant.sms.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tarrant.sms.exception.SmsException;
import com.tarrant.sms.model.SmsMessage;
import com.tarrant.sms.model.SmsStatus;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Message;
import com.twilio.sdk.resource.instance.Sms;

/**
 * Implementation of SmsService that is using Twilio as
 * the SMS service provider
 * 
 * @author declantarrant
 *
 */
@Component
public class TwilioService implements SmsService {

	private Account account;
	private String longCode;
	
	/**
	 * Instantiates the Twilio SMS Service by supplying the twilioAccount (via SPEL) 
	 * and the associated Twilio longCode from the Spring ApplicationContext 
	 * @param twilioAccount
	 * @param longCode
	 */
	@Autowired
	public TwilioService(@Value("#{twilioClient.account}") Account twilioAccount,
			@Value("${twilio.longcode}") String longCode) {
		this.account = twilioAccount;
		this.longCode = longCode;
	}
	
	/**
	 * Sends an SMS message to the chosen recipient using Twilio
	 * @param to
	 * @param message
	 */
	public String sendSMS(String to, String message) throws SmsException {
		 List<NameValuePair> params = new ArrayList<NameValuePair>();
		 params.add(new BasicNameValuePair("To", to)); 
		 params.add(new BasicNameValuePair("From", longCode));
		 params.add(new BasicNameValuePair("Body", message)); 
		 
		 Message sms = null;
		 try {
			 //MessageFactory mf = account.getMessageFactory();
			 sms = account.getMessageFactory().create(params);
		 }
		 catch (TwilioRestException tre) {
			 throw new SmsException("Twilio :: Error code : " + tre.getErrorCode() + ", Error Message : " + tre.getErrorMessage());
		 }
		 return sms.getSid();
	}
	
	/**
	 * Retrieve the status of the SMS message from Twilio
	 * @param to
	 * @param message
	 */
	public SmsMessage retrieveSentSMS(String msgCid) throws SmsException {
		try {
			Sms sms = account.getSms(msgCid);
			return convertToGenericSmsMessage(sms);
		}
		catch (RuntimeException tre) {
			throw new SmsException("Twilio :: Error Message : " + tre.getMessage());
		}
	}
	
	/**
	 * Converts a proprietary Twilio SMS to a generic representation
	 * to keep application agnostic to SMS service provider.
	 * @param twilioSms
	 * @return message
	 */ 
	protected SmsMessage convertToGenericSmsMessage(Sms twilioSms) {
		SmsMessage smsMessage = new SmsMessage();
		smsMessage.setSmsKey(twilioSms.getSid());
		smsMessage.setFrom(twilioSms.getFrom());
		smsMessage.setTo(twilioSms.getTo());
		smsMessage.setMsgBody(twilioSms.getBody());
		smsMessage.setDateSent(twilioSms.getDateSent());
		smsMessage.setStatus(SmsStatus.valueOf(twilioSms.getStatus().toUpperCase()));
		return smsMessage;
	}
	
}
