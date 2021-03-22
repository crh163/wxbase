package com.crh.wxbase.common.constant;


public enum ResponseCodeEnum {

    /**
     * 响应状态码
     */
    SUCCESS(200, "请求成功"),
    FAIL(500, "请求异常"),
    FAIL_LOGIN_NULL_USERNAMEORPASSWORD(5001, "登录失败，用户名或密码为空！"),
    FAIL_LOGIN_NULL_DB_USERNAME(5002, "登录失败，不存在该用户名！"),
    FAIL_LOGIN_ERROR_PASSWORD(5003, "登录失败，密码错误！"),
    FAIL_USER_DELETE_ADMIN(5004, "不能删除admin用户！"),


    FAIL_ALL_DELETE_NULL_ID(6001, "未查询到可删除内容，删除失败！"),
    FAIL_ALL_DELETE_PORTION_NULL_ID(6002, "待删除数据中有未存在数据，删除失败！"),
    FAIL_ALL_UPDATE_NULL_ID(6003, "未携带id值，修改失败！"),


    FAIL_INDEX_NULL_SEARCHTYPE(7001, "查询类型参数有误，请检查！");


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
