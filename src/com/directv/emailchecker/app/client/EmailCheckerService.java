/*
 * Author  : Meiyazhagan Arjunan
 * Company : Ilink Multitech Solutions
 */
package com.directv.emailchecker.app.client;

import com.directv.emailchecker.app.shared.Response;
import com.directv.emailchecker.app.util.ECException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

// TODO: Auto-generated Javadoc
/**
 * The Interface EmailCheckerService.
 */
@RemoteServiceRelativePath("emailchecker")
public interface EmailCheckerService extends RemoteService {

	/**
	 * Validate email id.
	 *
	 * @param emailId the email id
	 * @return the response
	 * @throws ECException the eC exception
	 */
	Response validateEmailId(String emailId) throws ECException;
}
