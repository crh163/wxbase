package com.crh.wxbase.system.controller;

import com.crh.wxbase.common.constant.CommonConsts;
import com.crh.wxbase.common.entity.QueryModel;
import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.common.entity.resp.Response;
import com.crh.wxbase.common.utils.ResponseUtil;
import com.crh.wxbase.system.entity.dto.LoginDto;
import com.crh.wxbase.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author rory.chen
 * @date 2021-01-18 14:18
 */
@RestController
@Api(tags = "管理后台用户")
@RequestMapping("/sysuser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("查询管理后台用户")
    @PostMapping("")
    public PageableItemsDto query(@RequestBody QueryModel queryModel) {
        return sysUserService.selectListByPage(queryModel);
    }

    @ApiOperation("管理后台登录")
    @PostMapping("/login")
    public Response login(HttpServletRequest request, LoginDto loginDto){
        return sysUserService.login(loginDto, request);
    }

    @ApiOperation("管理后台登出")
    @PostMapping("/loginout")
    public Response login(HttpServletRequest request){
        String token = request.getHeader(CommonConsts.X_ACCESS_TOKEN);
        request.getSession().removeAttribute(token);
        return ResponseUtil.getSuccess();
    }

}
