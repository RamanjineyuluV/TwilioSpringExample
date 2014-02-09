package com.tarrant.sms.model;

/**
 * Enum to express the possible statii of an SMS message. Certain providers may not
 * make use of all of these.
 *
 * @author declantarrant
 *
 */
public enum SmsStatus {

	QUEUED,
	SENDING,
	SENT,
	FAILED,
	RECEIVED
	
}
