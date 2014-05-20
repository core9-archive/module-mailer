package io.core9.module.mailer;

import java.util.Properties;




import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import net.minidev.json.JSONObject;

public class SendTLSMail {
	String username;
	String password;
	Properties mailProperties;
	MailMessage mailMessage;


	public SendTLSMail(String username, String password, TLSMailProperties tslMailProps, MailMessage mailMessage){
		this.username = username;
		this.password = password;
		this.mailProperties = tslMailProps.getMailProperties();
		this.mailMessage = mailMessage;
	}

	public JSONObject send() throws MessagingException{
		Session session = Session.getInstance(mailProperties,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mailMessage.getFrom()));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(mailMessage.getTo()));
			message.setSubject(mailMessage.getSubject());
			message.setText(mailMessage.getText());

			Transport.send(message);

			return (JSONObject) new JSONObject().put("Status", "Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
}