package com.crh.wxbase.system.controller;

import com.crh.wxbase.common.entity.resp.Response;
import com.crh.wxbase.common.utils.ResponseUtil;
import com.crh.wxbase.system.entity.dto.SysMenuDto;
import com.crh.wxbase.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author rory.chen
 * @date 2021-01-18 14:18
 */
@RestController
@Api(tags = "系统菜单")
@RequestMapping("/sysconfig")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation("根据用户名查询菜单")
    @PostMapping("/queryMenu")
    public Response queryMenu(String username) {
        List<SysMenuDto> sysMenuDtos = sysMenuService.queryMenu(username);
        //封装Index数据
        SysMenuDto indexMenu = new SysMenuDto();
        indexMenu.setComponent("Home");
        indexMenu.setChildren(sysMenuDtos);
        return ResponseUtil.getSuccess(indexMenu);
    }

}
