package io.core9.plugin.mailer;

import io.core9.plugin.javascript.JavascriptModule;
import io.core9.plugin.javascript.JavascriptModuleImpl;

import java.util.HashMap;
import java.util.Map;

import net.xeoh.plugins.base.annotations.PluginImplementation;



/**
 * LET OP, NASHORN MOET UIT BUILDPATH, MOET VIA GRADLE
 */

@PluginImplementation
public class MailerProviderImpl implements MailerProvider {

	@Override
	public Map<String, JavascriptModule> getModules() {

		Map<String, JavascriptModule> modules = new HashMap<>();

		JavascriptModule mailer = new MailerModuleImpl();
		mailer.setName("mailer");

		modules.put(mailer.getName(), mailer);

		return modules ;
	}
}

