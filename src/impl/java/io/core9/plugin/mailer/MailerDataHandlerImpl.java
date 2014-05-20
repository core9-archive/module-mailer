package io.core9.plugin.mailer;

import io.core9.plugin.admin.plugins.AdminConfigRepository;
import io.core9.plugin.filesmanager.FileRepository;
import io.core9.plugin.server.request.Request;
import io.core9.plugin.widgets.datahandler.DataHandler;
import io.core9.plugin.widgets.datahandler.DataHandlerFactoryConfig;

import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngine;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.injections.InjectPlugin;

@PluginImplementation
public class MailerDataHandlerImpl implements MailerDataHandler {

	@InjectPlugin
	private AdminConfigRepository configRepository;

	private NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
	// secure
	private ScriptEngine sengine = factory
			.getScriptEngine(new String[] { "--no-java" });

	@InjectPlugin
	private FileRepository repository;

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

				Map<String, Object> files = getJsFile(options, req);


				result.put("mailer", files);
				return result;
			}

			@Override
			public MailerDataHandlerConfig getOptions() {
				return (MailerDataHandlerConfig) options;
			}

			private Map<String, Object> getJsFile(
					final DataHandlerFactoryConfig options, Request req) {

				Map<String, Object> res = new HashMap<String, Object>();

				String fileStr = ((MailerDataHandlerConfig) options)
						.getMailerId(req);

				String[] files = getFiles(fileStr);

				for (String file : files) {
					Map<String, Object> tmpFile = repository
							.getFileContentsByName(req.getVirtualHost(), file);
					if (tmpFile != null) {
						res.putAll(tmpFile);
					}
				}

				return res;
			}

			public String[] getFiles(String fileStr){
				return fileStr.split(",");
			}
		};
	}

}
