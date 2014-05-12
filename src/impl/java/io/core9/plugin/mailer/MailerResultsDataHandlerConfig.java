package io.core9.plugin.mailer;

import io.core9.plugin.server.request.Request;
import io.core9.plugin.widgets.datahandler.DataHandlerDefaultConfig;
import io.core9.plugin.widgets.datahandler.DataHandlerFactoryConfig;
import io.core9.plugin.widgets.datahandler.DataHandlerGlobalString;

public class MailerResultsDataHandlerConfig extends DataHandlerDefaultConfig implements DataHandlerFactoryConfig {
	
	private DataHandlerGlobalString mailerResultsId;
	
	/**
	 * @return the menuName
	 */
	public DataHandlerGlobalString getMailerResultsId() {
		return mailerResultsId;
	}
	
	/**
	 * Return the mailerID from a global
	 * @param request
	 * @return
	 */
	public String getMailerResultsId(Request request) {
		if(mailerResultsId.isGlobal()) {
			return request.getContext(this.getComponentName() + ".mailerResultsId", mailerResultsId.getValue());
		}
		return mailerResultsId.getValue();
	}

	/**
	 * @param menuName the menuName to set
	 */
	public void setMailerResultsId(DataHandlerGlobalString mailerResultsId) {
		this.mailerResultsId = mailerResultsId;
	}

}
