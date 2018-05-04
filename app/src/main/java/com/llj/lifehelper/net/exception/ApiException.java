package com.llj.lifehelper.net.exception;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/10 13:05
 *    描述：
 * </pre>
 */
public class ApiException extends RuntimeException {

	private int errorCode;
	private String displayMessage;

	public ApiException() {
		super();
	}

	public ApiException(int errorCode, String displayMessage) {
		this.errorCode = errorCode;
		this.displayMessage = displayMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getDisplayMessage() {
		return displayMessage;
	}

	public void setDisplayMessage(String displayMessage) {
		this.displayMessage = displayMessage;
	}
}
