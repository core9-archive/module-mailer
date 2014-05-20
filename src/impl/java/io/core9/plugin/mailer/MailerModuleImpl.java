package io.core9.plugin.mailer;

import javax.mail.MessagingException;

import net.minidev.json.JSONObject;
import io.core9.module.mailer.MailMessage;
import io.core9.module.mailer.SendTLSMail;
import io.core9.module.mailer.TLSMailProperties;
import io.core9.plugin.javascript.JavascriptModule;

public class MailerModuleImpl implements JavascriptModule {

	private JSONObject json;

	@Override
	public String getName() {
		return "mailer";
	}

	@Override
	public void setName(String name) {
	}

	@Override
	public JSONObject getJson() {
		return json;
	}

	@Override
	public void setJson(JSONObject json) {
		this.json = json;

		JSONObject serverSettings = (JSONObject) json.get("mail");
		JSONObject mailMessage = (JSONObject) serverSettings.get("message");


		SendTLSMail newTLSMail = 
				new SendTLSMail((String) serverSettings.get("user"), 
						(String) serverSettings.get("password"), 
						new TLSMailProperties
						(		
								convertStringToBool(serverSettings.get("SSL")), 
								(String) serverSettings.get("smtpHost"), 
								Integer.parseInt((String) serverSettings.get("port")),
								convertStringToBool(serverSettings.get("auth"))), 
								new MailMessage(
										(String) mailMessage.get("from"), 
										(String) mailMessage.get("text"),
										(String) mailMessage.get("subject"),
										(String) mailMessage.get("to"),
										null, 
										null)
						);
		try {
			json.clear();
			json.put("result", newTLSMail.send());
		} catch (MessagingException e) {
			e.printStackTrace();
			json.clear();
			json.put("result", "fail");
		
		}

	}

	private boolean convertStringToBool(Object string){
		String s = (String) string;
		if(s.equals("true")){
			return true;
		}
			return false;
	}
}
