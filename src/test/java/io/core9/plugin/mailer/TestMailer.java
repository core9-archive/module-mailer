package io.core9.plugin.mailer;

import io.core9.plugin.javascript.JavascriptModule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.mail.MessagingException;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

import org.junit.Test;

public class TestMailer {
	@Test
	public void test() throws MessagingException, IOException{		
		MailerProviderImpl provider = new MailerProviderImpl();
		
		Map<String, JavascriptModule> modules = provider.getModules();

		System.out.println(modules);

		for( Entry<String, JavascriptModule> entry : modules.entrySet()){

			JavascriptModule module = entry.getValue();
			String jsonString = readFile("src/test/java/io/core9/module/mailer/mailersetup.txt");
			
			JSONObject json = (JSONObject) JSONValue.parse(jsonString);
				

			module.setJson(json);

			JSONObject result = module.getJson();
			System.out.println(result);


		}
	}
	
	public String readFile(String fileString) throws IOException
	{
		try(BufferedReader br = new BufferedReader(new FileReader(fileString))){
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			return sb.toString();
		}
	}
}