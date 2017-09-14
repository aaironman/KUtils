package com.ironman.kutils.model;


/**
 * 作者: 冯浩
 * 时间: 2017/9/7
 * 描述: HttpResultModel
 */
public class HttpResultModel<T> extends HttpBaseModel {

	private HttpResultDataModel<T> data;

	public HttpResultDataModel<T> getData() {
		return data;
	}

	public void setData(HttpResultDataModel<T> data) {
		this.data = data;
	}


}
