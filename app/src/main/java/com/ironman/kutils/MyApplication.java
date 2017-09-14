package com.ironman.kutils;

import android.app.Application;


/**
 * 作者: 冯浩
 * 时间: 2017/9/7
 * 描述: MyApplication
 */
public class MyApplication extends Application {
	
	private static MyApplication instance = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}
	

	public static synchronized MyApplication getInstance() {
		return instance;
	}
	
}
