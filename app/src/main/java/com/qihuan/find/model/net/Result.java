package com.qihuan.find.model.net;

/**
 * Result
 * Created by Qi on 2017/9/23.
 */

public class Result {
    private boolean refresh;
    private String msg;

    public boolean isRefresh() {
        return refresh;
    }

    public Result setRefresh(boolean refresh) {
        this.refresh = refresh;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
