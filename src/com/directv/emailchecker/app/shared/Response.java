/*
 * Author  : Meiyazhagan Arjunan
 * Company : Ilink Multitech Solutions
 */
package com.directv.emailchecker.app.shared;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// TODO: Auto-generated Javadoc
/**
 * The Class Response.
 */
@XmlRootElement(name = "response")
public class Response implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3268420841818772612L;

	/** The Constant STATUS. */
	public static final String STATUS = "status";

	/** The Constant EMAIL_CHECKER. */
	public static final String EMAIL_CHECKER = "emailchecker";

	/** The Constant ERROR_MESSAGE. */
	public static final String ERROR_MESSAGE = "error";

	/** The status. */
	private String status;

	/** The emailchecker. */
	private EmailChecker emailchecker;

	/** The error. */
	private ErrorMessage error;

	/**
	 * Instantiates a new response.
	 */
	public Response() {
		super();
	}

	/**
	 * Instantiates a new response.
	 *
	 * @param status the status
	 * @param emailchecker the emailchecker
	 * @param error the error
	 */
	public Response(String status, EmailChecker emailchecker, ErrorMessage error) {
		super();
		this.status = status;
		this.emailchecker = emailchecker;
		this.error = error;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the emailchecker.
	 *
	 * @return the emailchecker
	 */
	@XmlElement
	public EmailChecker getEmailchecker() {
		return emailchecker;
	}

	/**
	 * Sets the emailchecker.
	 *
	 * @param emailchecker the new emailchecker
	 */
	public void setEmailchecker(EmailChecker emailchecker) {
		this.emailchecker = emailchecker;
	}

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	@XmlElement
	public ErrorMessage getError() {
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error the new error
	 */
	public void setError(ErrorMessage error) {
		this.error = error;
	}

}
