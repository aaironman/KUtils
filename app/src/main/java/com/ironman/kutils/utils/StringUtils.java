package com.ironman.kutils.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * 作者: 冯浩
 * 时间: 2017/9/7
 * 描述: StringUtils
 */
public class StringUtils {

	public static String getClient(Context context) {
		String versionName = getAppVersionName(context);
		StringBuffer client = new StringBuffer("qmxmobile/")
				.append(versionName)
				.append("-android/")
				.append(Build.VERSION.RELEASE)
				.append("-"+ Build.MANUFACTURER)
				.append("-"+ Build.MODEL);
		return client.toString();
	}

	/**
	 * 获取版本号
	 *
	 * @param context
	 *
	 * @return
	 */
	public static String getAppVersionName(Context context) {
		String versionName = "";
		try {
			// ---get the package info---
			PackageManager pm = context.getPackageManager();
			PackageInfo packInfo = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = packInfo.versionName;
			if (versionName == null || versionName.length() <= 0) {
				return "";
			}
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionName;
	}




}
