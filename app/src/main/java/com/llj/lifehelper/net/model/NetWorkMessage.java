package com.llj.lifehelper.net.model;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/10 12:59
 *    描述：
 * </pre>
 */
public class NetWorkMessage<T> {
	private int code;
	private String message;
	private T data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
