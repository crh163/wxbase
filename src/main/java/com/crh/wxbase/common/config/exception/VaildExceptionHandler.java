package com.crh.wxbase.common.config.exception;

import com.crh.wxbase.common.entity.resp.Response;
import com.crh.wxbase.common.utils.ResponseUtil;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author rory.chen
 * @date 2021-02-25 18:30
 */
@ControllerAdvice
@ResponseBody
public class VaildExceptionHandler {

    /**
     * validation异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public Response handleArgumentNotValidException(BindException e) {
        String defaultMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseUtil.getFail(defaultMessage);
    }

    /**
     * 自定义异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(WxbaseException.class)
    public Response handleWxbaseException(WxbaseException e) {
        return ResponseUtil.getResult(e.getErrorCode(), e.getErrorMsg());
    }

}
