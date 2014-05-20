package io.core9.module.mailer;

import java.util.Map;
import java.util.Map.Entry;

import io.core9.plugin.javascript.JavascriptModule;
import io.core9.plugin.mailer.MailerProviderImpl;
import net.minidev.json.JSONObject;

import org.junit.Test;

public class TestMail {

	
	@Test
	public void test(){
		
		
		MailerProviderImpl provider = new MailerProviderImpl();
		
		Map<String, JavascriptModule> modules = provider.getModules();
		
		for(Entry<String, JavascriptModule> entry : modules.entrySet()){
			JavascriptModule module = entry.getValue();
			
			
			JSONObject json = getJsonFromTestFile("filepath");
			
			module.setJson(json);
			
			JSONObject jsonResult = module.getJson();
			
			
		}
		
		
	}

	private JSONObject getJsonFromTestFile(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
