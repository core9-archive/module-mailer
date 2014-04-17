package io.core9.plugin.mailer;

import io.core9.plugin.admin.plugins.AdminConfigRepository;
import io.core9.plugin.server.request.Request;
import io.core9.plugin.server.vertx.VertxServer;
import io.core9.plugin.widgets.datahandler.DataHandler;
import io.core9.plugin.widgets.datahandler.DataHandlerFactoryConfig;

import java.util.HashMap;
import java.util.Map;
import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.injections.InjectPlugin;

@PluginImplementation
public class MailerDataHandlerImpl implements
		MailerDataHandler<MailerDataHandlerConfig> {

	@InjectPlugin
	private AdminConfigRepository configRepository;

	@InjectPlugin
	private VertxServer server;

	@Override
	public String getName() {
		return "Mailer";
	}

	@Override
	public Class<? extends DataHandlerFactoryConfig> getConfigClass() {
		return MailerDataHandlerConfig.class;
	}

	@Override
	public DataHandler<MailerDataHandlerConfig> createDataHandler(
			final DataHandlerFactoryConfig options) {
		return new DataHandler<MailerDataHandlerConfig>() {

			private Map<String, Object> result = new HashMap<String, Object>();

			@Override
			public Map<String, Object> handle(Request req) {

				Map<String, Object> mailer = configRepository.readConfig(
						req.getVirtualHost(),
						((MailerDataHandlerConfig) options).getMailerId(req));

				result.put("mailer", mailer);
				return result;
			}

			@Override
			public MailerDataHandlerConfig getOptions() {
				return (MailerDataHandlerConfig) options;
			}
		};
	}
}
