package io.core9.plugin.mailer;


import java.io.IOException;
import java.io.InputStreamReader;

import com.google.common.io.CharStreams;

import io.core9.plugin.widgets.datahandler.DataHandler;
import io.core9.plugin.widgets.datahandler.DataHandlerGlobalString;
import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.injections.InjectPlugin;


@PluginImplementation
public class MailerWidgetImpl implements MailerWidget {

	@InjectPlugin
	private MailerDataHandler<MailerDataHandlerConfig> mailerDataHandler;
	
	private DataHandler<MailerDataHandlerConfig> handler;
	
	@Override
	public DataHandler<?> getDataHandler() {
		return handler;
	}

	@Override
    public void execute() {
		MailerDataHandlerConfig options = new MailerDataHandlerConfig();
		DataHandlerGlobalString mailerId = new DataHandlerGlobalString();
		mailerId.setGlobal(true);
		options.setNashornID(mailerId);
		handler = mailerDataHandler.createDataHandler(options);
    }


	@Override
    public String getName() {
	    return "mailer";
    }

	@Override
    public String getTemplate() {
		try {
			return CharStreams.toString(new InputStreamReader(this.getClass().getResourceAsStream("/mailer/template.soy")));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
    }

	@Override
    public String getTemplateName() {
		return "io.core9.mailer.template";
    }
}
