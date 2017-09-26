package com.ironman.kutils.utils;

/**
 * 作者: miaocong
 * 时间: 2017/9/20
 * 描述: app常量
 */
public class Constants {

	//测试环境
	public static final String BASEURL = "http://192.168.10.206/";

	/**
	 * status code
	 */
	public static final int SUCCESS = 200;
	public static final int ERROR = 0;

	//分页时 每页的条数
	public static final int PAGE_SIZE = 10;

	/**
	 * 未登录或者ssid失效
	 */
	public static final int SSID_IS_INVALID_ERROR = 402;

	public static final String KEY_API = "52b7ec3471ac3bec6846577e79f20e4c"; //需要APIKEY请去 http://www.tianapi.com/#wxnew 申请,复用会减少访问可用次数

}
