package io.core9.plugin.mailer;

import io.core9.plugin.server.request.Request;
import io.core9.plugin.widgets.Core9GlobalConfiguration;
import io.core9.plugin.widgets.datahandler.DataHandlerDefaultConfig;
import io.core9.plugin.widgets.datahandler.DataHandlerGlobalString;

public class MailerDataHandlerConfig extends DataHandlerDefaultConfig {
	
	@Core9GlobalConfiguration(type = "mailer")
	private DataHandlerGlobalString mailerID;

	/**
	 * @return the menuName
	 */
	public DataHandlerGlobalString getMailerId() {
		return mailerID;
	}
	
	/**
	 * Return the mailerID from a global
	 * @param request
	 * @return
	 */
	public String getMailerId(Request request) {
		if(mailerID.isGlobal()) {
			return request.getContext(this.getComponentId() + ".mailerID", mailerID.getValue());
		}
		return mailerID.getValue();
	}

	/**
	 * @param menuName the menuName to set
	 */
	public void setMailerId(DataHandlerGlobalString mailerId) {
		this.mailerID = mailerId;
	}

}
