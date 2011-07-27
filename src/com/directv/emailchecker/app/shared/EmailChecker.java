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
 * The Class EmailChecker.
 */
@XmlRootElement(name = "emailchecker")
public class EmailChecker implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4827383878395740874L;

	/** The emailid. */
	private String emailid;

	/** The isvalid. */
	private boolean isvalid;

	/** The content. */
	private String content;

	/**
	 * Instantiates a new email checker.
	 */
	public EmailChecker() {

	}

	/**
	 * Instantiates a new email checker.
	 *
	 * @param emailid the emailid
	 * @param isvalid the isvalid
	 * @param content the content
	 */
	public EmailChecker(String emailid, boolean isvalid, String content) {
		super();
		this.emailid = emailid;
		this.isvalid = isvalid;
		this.content = content;
	}

	/**
	 * Gets the emailid.
	 *
	 * @return the emailid
	 */
	@XmlElement
	public String getEmailid() {
		return emailid;
	}

	/**
	 * Sets the emailid.
	 *
	 * @param emailid the new emailid
	 */
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	/**
	 * Checks if is isvalid.
	 *
	 * @return true, if is isvalid
	 */
	@XmlElement
	public boolean isIsvalid() {
		return isvalid;
	}

	/**
	 * Sets the isvalid.
	 *
	 * @param isvalid the new isvalid
	 */
	public void setIsvalid(boolean isvalid) {
		this.isvalid = isvalid;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	@XmlElement
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content the new content
	 */
	public void setContent(String content) {
		this.content = content;
	}

}
