package com.ironman.kutils.utils;


import com.ironman.kutils.data.network.AccountException;
import com.ironman.kutils.data.network.SsidException;

import rx.Observable;
import rx.functions.Func1;

/**
 * 处理sessionKey失效情况，刷新sessionKey并回调刷新当前接口
 * Created by 冯浩 on 16/11/30.
 */
public class RefreshSessionKeyAndRetryUtils {

	private static final String TAG = "RefreshSessionKeyAndRetryUtils";

	public static <T> Func1<Throwable, ? extends Observable<? extends T>> refreshSsidAndRetry(final Observable<T>
																									  toBeResumed) {
		return new Func1<Throwable, Observable<? extends T>>() {
			@Override
			public Observable<? extends T> call(Throwable throwable) {
				throwable.printStackTrace();

//				if (isSessionKeyFail(throwable)) {
//					return ApiFactory.getApiHelper().getSessionKey()
//							.flatMap(new Func1<HttpResultModel, Observable<UserInfoResultModel>>() {
//								@Override
//								public Observable<UserInfoResultModel> call(HttpResultModel resultModel) {
//									//获取sessionKey 失败则需要重新登录
//									if (resultModel.getResultCode() == Constants.SUCCESS) {
//										AppPreferencesHelper.getInstance().setSessionKey(resultModel.getSession_key());
//										String account = AppPreferencesHelper.getInstance().getAccount();
//										String autoPwd = AppPreferencesHelper.getInstance().getAutoPwd();
//										Map<String,String> params = new HashMap<>();
//										params.put("account",account);
//										params.put("password",autoPwd);
//										return ApiFactory.getApiHelper().login(params);
//									} else {
//										return Observable.error(new Exception("账号异常，退出登录"));
//									}
//								}
//							})
//							.flatMap(new Func1<UserInfoResultModel, Observable<T>>() {
//								@Override
//								public Observable<T> call(UserInfoResultModel userInfoResultModel) {
//									//使用autopwd登录 失败则需要重新登录
//									if (userInfoResultModel.getResultCode() == Constants.SUCCESS) {
////										AppPreferencesHelper.getInstance().saveLoginInfo(userInfoResultModel);
//										return toBeResumed;
//									} else {
//										return Observable.error(new Exception("账号异常，退出登录"));
//									}
//								}
//							})
//							.onErrorReturn(new Func1<Throwable, T>() {
//								@Override
//								public T call(Throwable throwable) {
////									AccountUtils.accountExceptionLogout();
//									return null;
//								}
//							});
//				}

				if (isAccountFail(throwable)) {
//					AccountUtils.accountExceptionLogout();
					return Observable.error(new Exception("账号异常，退出登录"));
				}

				return Observable.error(throwable);
			}

			//用户未登录或会话已过期
			public boolean isSessionKeyFail(Throwable throwable) {
				boolean b = false;

				if (throwable instanceof SsidException) {
					b = true;
				}
				return b;
			}

			//账户状态异常
			public boolean isAccountFail(Throwable throwable) {
				boolean b = false;

				if (throwable instanceof AccountException) {
					b = true;
				}
				return b;
			}

		};
	}

}
