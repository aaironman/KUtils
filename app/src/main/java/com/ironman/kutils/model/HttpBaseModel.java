package com.ironman.kutils.model;

import com.google.gson.annotations.SerializedName;

/**
 * 作者: 冯浩
 * 时间: 2017/9/7
 * 描述: HttpBaseModel
 */
public class HttpBaseModel {

    @SerializedName("ret")
    private int resultCode;

    private String msg;
    private String ssid;
    private String time;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
