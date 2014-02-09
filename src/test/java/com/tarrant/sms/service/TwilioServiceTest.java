package com.tarrant.sms.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;

import com.tarrant.sms.exception.SmsException;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Message;

public class TwilioServiceTest {

	private TwilioService twilioService;
	private MessageFactory messageFactory;
	private Account mockAccount;
	
	private String dummyLongCode = "12345";

	@Before
	public void setup() {
		mockAccount = mock(Account.class);
		messageFactory = mock(MessageFactory.class);
		twilioService = new TwilioService(mockAccount, dummyLongCode);
	}

	@Test
	public void testSendSMS() throws TwilioRestException {

		String to = "phonenumber";
		String message = "dummy message";
		String expectedMsgKey = "12345";
				
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("To", to)); 
		params.add(new BasicNameValuePair("From", dummyLongCode));
		params.add(new BasicNameValuePair("Body", message)); 
		
		Message msg = mock(Message.class);
		
		when(mockAccount.getMessageFactory()).thenReturn(messageFactory);
		when(messageFactory.create(params)).thenReturn(msg);
		when(msg.getSid()).thenReturn("12345");
		
		String actualMsgKey = twilioService.sendSMS(to, message);
		
		verify(mockAccount).getMessageFactory();
		verify(messageFactory).create(params);
		verify(msg).getSid();

		assertEquals(expectedMsgKey,actualMsgKey);

	}
	
	@Test(expected=SmsException.class)
	public void testSendSMSErrorCondition() throws TwilioRestException {

		String to = "phonenumber";
		String message = "dummy message";
				
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("To", to)); 
		params.add(new BasicNameValuePair("From", dummyLongCode));
		params.add(new BasicNameValuePair("Body", message)); 
		
		when(mockAccount.getMessageFactory()).thenReturn(messageFactory);
		when(messageFactory.create(params)).thenThrow(new TwilioRestException("",1));
		
		twilioService.sendSMS(to, message);
		
		verify(mockAccount).getMessageFactory();
		verify(messageFactory).create(params);

	}
	
}
