package com.crh.wxbase.gsc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rory.chen
 * @date 2021-01-18 14:18
 */
@RestController
@Api(tags = "系统配置")
@RequestMapping("/sysconfig")
public class SysConfigController {

    @ApiOperation("查询系统配置")
    @GetMapping(value = "/get")
    public String get(){
        return "123";
    }

}
