package io.core9.module.mailer;

import java.util.Properties;

public class TLSMailProperties {
	Properties mailProperties;

	public TLSMailProperties(boolean SSL, String smtpHost, int port, boolean auth){ 
		mailProperties = new Properties();
		mailProperties.put("mail.smtp.host", smtpHost);
		mailProperties.put("mail.smtp.auth", auth);  // If you need to authenticate
		mailProperties.put("mail.smtp.port", port);  // If you need to authenticate
		mailProperties.put("mail.smtp.starttls.enable","true");
		
		// Use the following if you need SSL
		if(SSL){
//			mailProperties.put("mail.smtp.socketFactory.port", port);
//			mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//			mailProperties.put("mail.smtp.socketFactory.fallback", "false");
		}
	}

	public Properties getMailProperties() {
		return mailProperties;
	}

	public void setMailProperties(Properties mailProperties) {
		this.mailProperties = mailProperties;
	}
	
	
}