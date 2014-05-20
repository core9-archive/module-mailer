package io.core9.module.mailer;


public class MailMessage {
	String from;
	String text;
	String subject;
	String to;
	String bcc;
	String cc;
	
	public MailMessage(String from, String text, String subject, String to, String bcc, String cc){
		this.from = from;
		this.text = text;
		this.subject = subject;
		this.to = to;
		this.bcc = bcc;
		this.cc = cc;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}
}
