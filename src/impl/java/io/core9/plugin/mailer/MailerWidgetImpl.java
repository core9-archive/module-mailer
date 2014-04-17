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
	private MailerDataHandler<MailerDataHandlerConfig> nashornDataHandler;
	
	private DataHandler<MailerDataHandlerConfig> handler;
	
	@Override
	public DataHandler<?> getDataHandler() {
		return handler;
	}

	@Override
    public void execute() {
		MailerDataHandlerConfig options = new MailerDataHandlerConfig();
		DataHandlerGlobalString NashornId = new DataHandlerGlobalString();
		NashornId.setGlobal(true);
		options.setNashornID(NashornId);
		handler = nashornDataHandler.createDataHandler(options);
    }


	@Override
    public String getName() {
	    return "nashorn_js";
    }

	@Override
    public String getTemplate() {
		try {
			return CharStreams.toString(new InputStreamReader(this.getClass().getResourceAsStream("/nashorn/template.soy")));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
    }

	@Override
    public String getTemplateName() {
		return "io.core9.nashorn.script";
    }
}
