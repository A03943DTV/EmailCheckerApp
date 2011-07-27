/*
 * Author  : Meiyazhagan Arjunan
 * Company : Ilink Multitech Solutions
 */
package com.directv.emailchecker.app.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.directv.emailchecker.app.client.EmailCheckerService;
import com.directv.emailchecker.app.shared.EmailChecker;
import com.directv.emailchecker.app.shared.ErrorMessage;
import com.directv.emailchecker.app.shared.Response;
import com.directv.emailchecker.app.util.ECException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailCheckerServiceImpl.
 */
@SuppressWarnings("serial")
public class EmailCheckerServiceImpl extends RemoteServiceServlet implements EmailCheckerService {

	/** The Constant TARGET_URL. */
	private static final String TARGET_URL = "http://204.236.227.134:8080/EmailCheckerServiceApache/rest/directv/emailchecker/";

	/**
	 * Overridden Method
	 * @param emailId
	 * @return
	 * @throws ECException 
	 */
	@Override
	public Response validateEmailId(String emailId) throws ECException {

		URL url;
		HttpURLConnection connection = null;
		Response response = null;

		try {

			url = new URL(TARGET_URL + emailId);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setConnectTimeout(60000);//1 minute
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			int statusCode = connection.getResponseCode();
			if (statusCode != 200) {
				throw new ECException("Invalid HTTP response status code " + statusCode + " from web service server.");
			}

			//Get Response	
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String jsonData = br.readLine();
			response = getBeanFromJson(jsonData);

		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof ECException) {
				throw new ECException(e.getMessage());
			} else {
				throw new ECException("Exception occured while connecting to URL : " + TARGET_URL);
			}
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return response;
	}

	/**
	 * Gets the bean from json.
	 *
	 * @param jsonData the json data
	 * @return the bean from json
	 */
	@SuppressWarnings("unchecked")
	private Response getBeanFromJson(String jsonData) {

		Map mappingResources = new HashMap();
		mappingResources.put(Response.EMAIL_CHECKER, EmailChecker.class);
		mappingResources.put(Response.ERROR_MESSAGE, ErrorMessage.class);
		return (Response) JSONObject.toBean(JSONObject.fromObject(jsonData), Response.class, mappingResources);
	}

}
