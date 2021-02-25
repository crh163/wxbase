package com.crh.wxbase.system.controller;

import com.crh.wxbase.common.constant.CommonConsts;
import com.crh.wxbase.common.constant.EncryptConsts;
import com.crh.wxbase.common.constant.ResponseCodeEnum;
import com.crh.wxbase.common.constant.UserLevelConsts;
import com.crh.wxbase.common.entity.QueryModel;
import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.common.entity.resp.Response;
import com.crh.wxbase.common.utils.ArithmeticUtil;
import com.crh.wxbase.common.utils.ResponseUtil;
import com.crh.wxbase.system.entity.SysUser;
import com.crh.wxbase.system.entity.dto.LoginDto;
import com.crh.wxbase.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author rory.chen
 * @date 2021-01-18 14:18
 */
@RestController
@Api(tags = "管理后台用户")
@RequestMapping("/sysuser")
public class SysUserController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("查询管理后台用户")
    @PostMapping("")
    public PageableItemsDto query(@RequestBody QueryModel queryModel) {
        return sysUserService.selectListByPage(queryModel);
    }

    @ApiOperation("管理后台用户新增")
    @PostMapping("/insert")
    public Response insert(@Valid SysUser sysUser){
        //密码加密
        String passwordEncryAes = ArithmeticUtil.encryptAes(sysUser.getPassword(), EncryptConsts.AES_KEY);
        sysUser.setPassword(passwordEncryAes);
        return ResponseUtil.getResult(sysUserService.save(sysUser));
    }

    @ApiOperation("管理后台用户修改")
    @PostMapping("/update")
    public Response update(SysUser sysUser){
        //用户名和密码不能修改
        sysUser.setUsername(null);
        return ResponseUtil.getResult(sysUserService.updateById(sysUser));
    }

    @ApiOperation("管理后台用户删除")
    @PostMapping("/deleteByIds")
    public Response deleteByIds(List<Long> ids){
        for(Long id : ids){
            SysUser user = sysUserService.getById(id);
            if(UserLevelConsts.LEVEL_ADMIN_USER.equals(user.getUsername())){
                return ResponseUtil.getFail(ResponseCodeEnum.FAIL_USER_DELETE_ADMIN);
            }
        }
        sysUserService.removeByIds(ids);
        return ResponseUtil.getSuccess();
    }

    @ApiOperation("管理后台登录")
    @PostMapping("/login")
    public Response login(LoginDto loginDto){
        return sysUserService.login(loginDto, request);
    }

    @ApiOperation("管理后台登出")
    @PostMapping("/loginout")
    public Response login(){
        String token = request.getHeader(CommonConsts.X_ACCESS_TOKEN);
        request.getSession().removeAttribute(token);
        return ResponseUtil.getSuccess();
    }

}
