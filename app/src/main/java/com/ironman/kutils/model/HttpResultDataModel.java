package com.ironman.kutils.model;

/**
 * 作者: 冯浩
 * 时间: 2017/9/7
 * 描述: HttpResultDataModel
 */
public class HttpResultDataModel<T> {

	private T list;

	private int pagenum;

	private int currentpage;

	public T getList() {
		return list;
	}

	public void setList(T list) {
		this.list = list;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
}
