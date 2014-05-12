package io.core9.plugin.mailer;

import io.core9.plugin.admin.plugins.AdminConfigRepository;
import io.core9.plugin.server.request.Request;
import io.core9.plugin.widgets.datahandler.DataHandler;
import io.core9.plugin.widgets.datahandler.DataHandlerFactoryConfig;

import java.util.HashMap;
import java.util.Map;
import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.injections.InjectPlugin;

@PluginImplementation
public class MailerResultsDataHandlerImpl implements MailerResultsDataHandler {

	@InjectPlugin
	private AdminConfigRepository configRepository;



	@Override
	public String getName() {
		return "MailerResults";
	}

	@Override
	public Class<? extends DataHandlerFactoryConfig> getConfigClass() {
		return MailerResultsDataHandlerConfig.class;
	}

	@Override
	public DataHandler<MailerResultsDataHandlerConfig> createDataHandler(
			final DataHandlerFactoryConfig options) {
		return new DataHandler<MailerResultsDataHandlerConfig>() {

			private Map<String, Object> result = new HashMap<String, Object>();

			@Override
			public Map<String, Object> handle(Request req) {

				Map<String, Object> mailerResults = configRepository.readConfig(
						req.getVirtualHost(),
						((MailerResultsDataHandlerConfig) options).getMailerResultsId(req));
				result.put("mailerresult", mailerResults);
				return result;
			}

			@Override
			public MailerResultsDataHandlerConfig getOptions() {
				return (MailerResultsDataHandlerConfig) options;
			}
		};
	}
}
