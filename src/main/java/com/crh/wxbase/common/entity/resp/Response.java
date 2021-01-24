package com.crh.wxbase.common.entity.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private Integer code;

    private String msg;

    private Object data;

    public Response(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
