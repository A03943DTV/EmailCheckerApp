/*
 * Author  : Meiyazhagan Arjunan
 * Company : Ilink Multitech Solutions
 */
package com.directv.emailchecker.app.client;

import com.directv.emailchecker.app.shared.Response;
import com.google.gwt.user.client.rpc.AsyncCallback;

// TODO: Auto-generated Javadoc
/**
 * The Interface EmailCheckerServiceAsync.
 */
public interface EmailCheckerServiceAsync {

	/**
	 * Validate email id.
	 *
	 * @param emailId the email id
	 * @param callback the callback
	 */
	void validateEmailId(String emailId, AsyncCallback<Response> callback);
}
