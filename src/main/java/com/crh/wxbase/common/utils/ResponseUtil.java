package com.crh.wxbase.common.utils;

import com.crh.wxbase.common.constant.ResponseCodeEnum;
import com.crh.wxbase.common.entity.resp.Response;

import java.util.Map;

public class ResponseUtil {

    /**
     * 成功
     *
     * @return
     */
    public static Response getSuccess() {
        return new Response(ResponseCodeEnum.SUCCESS.getCode(),
                ResponseCodeEnum.SUCCESS.getMsg());
    }


    /**
     * 成功
     *
     * @return
     */
    public static Response getSuccess(Map<String, Object> data) {
        return new Response(ResponseCodeEnum.SUCCESS.getCode(),
                ResponseCodeEnum.SUCCESS.getMsg(), data);
    }


    /**
     * 成功
     *
     * @return
     */
    public static Response getSuccess(Object data) {
        return new Response(ResponseCodeEnum.SUCCESS.getCode(),
                ResponseCodeEnum.SUCCESS.getMsg(), data);
    }

    /**
     * 失败
     *
     * @return
     */
    public static Response getFail() {
        return new Response(ResponseCodeEnum.FAIL.getCode(),
                ResponseCodeEnum.FAIL.getMsg());
    }

    /**
     * 失败
     *
     * @return
     */
    public static Response getFail(String failMsg) {
        return new Response(ResponseCodeEnum.FAIL.getCode(), failMsg);
    }

    /**
     * 根据枚举动态
     *
     * @return
     */
    public static Response getFail(ResponseCodeEnum codeEnum) {
        return new Response(codeEnum.getCode(), codeEnum.getMsg());
    }

    /**
     * 根据布尔值
     *
     * @return
     */
    public static Response getResult(Boolean bool) {
        ResponseCodeEnum codeEnum = bool ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.FAIL;
        return new Response(codeEnum.getCode(), codeEnum.getMsg());
    }

}
