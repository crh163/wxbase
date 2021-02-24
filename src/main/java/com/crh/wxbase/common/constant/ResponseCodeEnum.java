package com.crh.wxbase.common.constant;


public enum ResponseCodeEnum {

    /**
     * 响应状态码
     */
    SUCCESS(200, "请求成功"),
    FAIL(500, "请求异常"),
    FAIL_LOGIN_NULL_USERNAMEORPASSWORD(5001, "登录失败，用户名或密码为空！"),
    FAIL_LOGIN_NULL_DB_USERNAME(5002, "登录失败，不存在该用户名！"),
    FAIL_LOGIN_ERROR_PASSWORD(5003, "登录失败，密码错误！");

    private Integer code;

    private String msg;

    ResponseCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
