package com.tarrant.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarrant.sms.model.SmsMessage;
import com.tarrant.sms.model.SmsStatus;
import com.tarrant.sms.service.TwilioService;

public class Application {

	static final Logger LOG = LoggerFactory.getLogger(Application.class);
	private ApplicationContext appContext;

	private TwilioService twilioManager;
	
	public static void main (String args[]) {
		Application app = new Application();
		app.demoSMS();
	}
	
	public Application() {
		appContext = new ClassPathXmlApplicationContext("classpath:META-INF/app-context.xml");
		twilioManager = appContext.getBean("twilioService", TwilioService.class);
	}
	
	/**
	 * Code example for sending an SMS 
	 */
	public void demoSMS() {

		// Send SMS message
		String smsKey = twilioManager.sendSMS("+353877826636", "test");
		LOG.info("Confirmation SID : " + smsKey);
		
		// Retrieve SMS status and details until it reaches a state of sent or failed
		SmsMessage smsMessage;
		do {
			smsMessage = twilioManager.retrieveSentSMS(smsKey);
			LOG.info("Message Status : " + smsMessage.getStatus().toString());
		}
		while (!smsMessage.getStatus().equals(SmsStatus.SENT) && !smsMessage.getStatus().equals(SmsStatus.FAILED));
		
		// print full sms message details
		LOG.info(smsMessage.toString());
	}

}
