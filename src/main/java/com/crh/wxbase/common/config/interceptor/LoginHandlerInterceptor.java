package com.crh.wxbase.common.config.interceptor;

import com.crh.wxbase.common.constant.CommonConsts;
import com.crh.wxbase.system.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rory.chen
 * @date 2021-02-24 17:44
 */
@Slf4j
public class LoginHandlerInterceptor implements HandlerInterceptor {

    /**
     * 登录拦截器
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(CommonConsts.X_ACCESS_TOKEN);
        Object user = request.getSession().getAttribute(token);
        if (user != null) {
            SysUser sysUser = (SysUser) user;
            request.setAttribute(CommonConsts.USERINFO, sysUser);
            return true;
        } else {
//            log.info("未登录无法访问url：{}", request.getRequestURL());
//            request.getRequestDispatcher("/sysuser/login").forward(request, response);
//            return false;
            return true;
        }
    }

}
