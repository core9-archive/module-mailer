package io.core9.plugin.mailer;

import io.core9.plugin.server.request.Request;
import io.core9.plugin.widgets.datahandler.DataHandlerDefaultConfig;
import io.core9.plugin.widgets.datahandler.DataHandlerFactoryConfig;
import io.core9.plugin.widgets.datahandler.DataHandlerGlobalString;

public class MailerDataHandlerConfig extends DataHandlerDefaultConfig implements DataHandlerFactoryConfig {
	
	private DataHandlerGlobalString mailerId;
	
	/**
	 * @return the menuName
	 */
	public DataHandlerGlobalString getMailerId() {
		return mailerId;
	}
	
	/**
	 * Return the mailerID from a global
	 * @param request
	 * @return
	 */
	public String getMailerId(Request request) {
		if(mailerId.isGlobal()) {
			return request.getContext(this.getComponentName() + ".mailerId", mailerId.getValue());
		}
		return mailerId.getValue();
	}

	/**
	 * @param menuName the menuName to set
	 */
	public void setMailerId(DataHandlerGlobalString mailerId) {
		this.mailerId = mailerId;
	}

}
