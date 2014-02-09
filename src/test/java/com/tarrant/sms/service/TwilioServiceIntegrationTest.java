package com.tarrant.sms.service;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tarrant.sms.Application;
import com.tarrant.sms.exception.SmsException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/app-context-test.xml" })
public class TwilioServiceIntegrationTest {
	
	static final Logger LOG = LoggerFactory.getLogger(Application.class);
	
	@Autowired
	TwilioService smsNotificationService;
	
	@Test
	public void testSendSMS() {
		String smsKey = smsNotificationService.sendSMS("+353877826636", "dummy message");
		assertNotNull(smsKey);
	}
	
	@Test(expected=SmsException.class)
	public void testSendInvalidSMS() {
		try {
			smsNotificationService.sendSMS("3453453543534534535345", "dummymessage");
		}
		catch (SmsException smsEx){
			// Catch exception to print for visual purposes but then throw again
			LOG.error(smsEx.getMessage());
			throw smsEx;
		}
	}
	
}
