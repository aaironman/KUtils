package com.ironman.kutils.data.network;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.ironman.kutils.data.network.rxJavaCallAdapter.HttpException;

import org.json.JSONException;

import java.net.ConnectException;

/**
 * 作者: 冯浩
 * 时间: 2017/9/7
 * 描述: 错误处理
 */
public class ExceptionHandle {

	/**
	 * 网络错误
	 */
	public static final int NETWORK_ERROR = 1000;
	/**
	 * 其他错误
	 */
	public static final int UNEXPECTED = 1001;
	/**
	 * 200情况，ret为0的时候错误
	 */
	public static final int HTTP_ERROR = 1002;


	public static ResponseThrowable handleException(Throwable e) {
		ResponseThrowable ex;
		if (e instanceof HttpException
				||e instanceof ConnectException) {
			Exception exception = (Exception) e;
			ex = new ResponseThrowable(exception, NETWORK_ERROR);
			ex.message = "网络错误";
			return ex;
		} else if (e instanceof JsonParseException
				|| e instanceof JSONException
				|| e instanceof ParseException){
			ex = new ResponseThrowable(e, UNEXPECTED);//均视为解析错误
			ex.message = "解析错误";
			return ex;
		}else {
			ex = new ResponseThrowable(e, UNEXPECTED);
			ex.message = "未知错误";
			return ex;
		}
	}

	public static class ResponseThrowable extends Exception {
		public int code;
		public String message;

		public ResponseThrowable(Throwable throwable, int code) {
			super(throwable);
			this.code = code;
		}
	}


}
