package com.crh.wxbase.system.controller;

import com.crh.wxbase.common.entity.QueryModel;
import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.system.service.SysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author rory.chen
 * @date 2021-01-18 14:18
 */
@RestController
@Api(tags = "系统配置")
@RequestMapping("/sysconfig")
public class SysConfigController {

    @Autowired
    private SysConfigService sysConfigService;

    @ApiOperation("查询系统配置")
    @PostMapping("")
    public PageableItemsDto query(@RequestBody QueryModel queryModel) {
        return sysConfigService.selectListByPage(queryModel);
    }

}
