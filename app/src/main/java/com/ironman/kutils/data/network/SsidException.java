package com.ironman.kutils.data.network;

import retrofit2.Response;


/**
 * 作者: 冯浩
 * 时间: 2017/9/7
 * 描述: ssid错误处理
 */
public class SsidException extends Exception {

	private static String getMessage(Response<?> response) {
		if (response == null) throw new NullPointerException("response == null");
		return "HTTP " + response.code() + " " + response.message();
	}

	private final int code;
	private final String message;
	private final transient Response<?> response;

	public SsidException(Response<?> response) {
		super(getMessage(response));
		this.code = response.code();
		this.message = response.message();
		this.response = response;
	}

	/** HTTP status code. */
	public int code() {
		return code;
	}

	/** HTTP status message. */
	public String message() {
		return message;
	}

	/**
	 * The full HTTP response. This may be null if the exception was serialized.
	 */
	public Response<?> response() {
		return response;
	}
}