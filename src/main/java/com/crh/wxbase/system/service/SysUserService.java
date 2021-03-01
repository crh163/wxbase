package com.crh.wxbase.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crh.wxbase.common.constant.ColumnConsts;
import com.crh.wxbase.common.constant.EncryptConsts;
import com.crh.wxbase.common.constant.ResponseCodeEnum;
import com.crh.wxbase.common.entity.resp.Response;
import com.crh.wxbase.common.service.BaseService;
import com.crh.wxbase.common.utils.ArithmeticUtil;
import com.crh.wxbase.common.utils.ResponseUtil;
import com.crh.wxbase.system.entity.SysUser;
import com.crh.wxbase.system.entity.dto.LoginReqDto;
import com.crh.wxbase.system.entity.dto.LoginResDto;
import com.crh.wxbase.system.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author rory.chen
 * @date 2021-01-21 18:06
 */
@Service
@Slf4j
public class SysUserService extends BaseService<SysUserMapper, SysUser> {

    /**
     * 管理后台登录验证
     *
     * @param loginReqDto
     * @param request
     */
    public Response login(LoginReqDto loginReqDto, HttpServletRequest request) {
        if(StringUtils.isBlank(loginReqDto.getUsername()) || StringUtils.isBlank(loginReqDto.getPassword())){
            return ResponseUtil.getFail(ResponseCodeEnum.FAIL_LOGIN_NULL_USERNAMEORPASSWORD);
        }
        //查询是否存在该用户
        SysUser sysUser = getOne(new QueryWrapper<SysUser>().eq(ColumnConsts.USERNAME, loginReqDto.getUsername()));
        if(Objects.isNull(sysUser)){
            return ResponseUtil.getFail(ResponseCodeEnum.FAIL_LOGIN_NULL_DB_USERNAME);
        }
        //对明文密码进行加密比较
        String passwordEncryAes = ArithmeticUtil.encryptAes(loginReqDto.getPassword(), EncryptConsts.AES_KEY);
        if(!sysUser.getPassword().equals(passwordEncryAes)){
            return ResponseUtil.getFail(ResponseCodeEnum.FAIL_LOGIN_ERROR_PASSWORD);
        }
        log.info("用户名：{} 登录成功！", loginReqDto.getUsername());
        HttpSession session = request.getSession();
        session.setAttribute(session.getId(), sysUser);
        return ResponseUtil.getSuccess(new LoginResDto(session.getId(), sysUser));
    }

}
