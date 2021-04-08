package com.crh.wxbase.common.config.interceptor;

import com.crh.wxbase.common.constant.CommonConsts;
import com.crh.wxbase.common.utils.SpringContextUtil;
import com.crh.wxbase.system.entity.SysWxUser;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rory.chen
 * @date 2021-02-24 17:44
 */
@Slf4j
public class ApiLoginHandlerInterceptor implements HandlerInterceptor {

    /**
     * api登录拦截器
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
        RedisTemplate redisTemplate = SpringContextUtil.getBean("redisTemplate", RedisTemplate.class);
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String userInfo = opsForValue.get(token);
        if (userInfo != null) {
            SysWxUser sysWxUser = new Gson().fromJson(userInfo, SysWxUser.class);
            request.setAttribute(CommonConsts.USERINFO, sysWxUser);
            return true;
        } else {
            return true;
        }
    }

}
