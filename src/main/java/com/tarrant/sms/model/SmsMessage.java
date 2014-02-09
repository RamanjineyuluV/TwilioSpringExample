package com.tarrant.sms.model;

import java.util.Date;

/**
 * Generic SMS message
 * 
 * @author declantarrant
 *
 */
public class SmsMessage {

	private String smsKey;
	
	private String to;
	private String from;
	private String msgBody;
	
	private SmsStatus status;
	private Date dateSent;
	
	public String getSmsKey() {
		return smsKey;
	}

	public void setSmsKey(String smsKey) {
		this.smsKey = smsKey;
	}

	public String getTo() {
		return to;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getMsgBody() {
		return msgBody;
	}
	
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	
	public SmsStatus getStatus() {
		return status;
	}
	
	public void setStatus(SmsStatus status) {
		this.status = status;
	}
	
	public Date getDateSent() {
		return dateSent;
	}
	
	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}

	@Override
	public String toString() {
		return "SmsMessage [smsKey=" + smsKey + ", to=" + to + ", from=" + from
				+ ", msgBody=" + msgBody + ", status=" + status + ", dateSent="
				+ dateSent + "]";
	}
	
}
