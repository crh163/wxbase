package com.crh.wxbase.common.config.aspect;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author rory.chen
 * @date 2021-02-02 18:32
 */
@Aspect
@Component
@Slf4j
public class ControllerLogAspect {

    @Autowired
    private Gson gson;

    @Pointcut("execution(* com.crh.wxbase..controller.*.*(..))")
    public void controllerLog() {
    }

    @Before("controllerLog()")
    public void logBeforeController(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 记录下请求内容
        log.info("URL : {}", request.getRequestURL().toString());
        log.info("HTTP METHOD : {}", request.getMethod());
        log.info("IP : {}", request.getRemoteAddr());
        log.info("CLASS_METHOD : {}", joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        log.info("PARAMS : {}", gson.toJson(joinPoint.getArgs()));
    }


    @AfterReturning(returning = "ret", pointcut = "controllerLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        //对每个请求的结果打印的字符串最大长度
        int maxLength = 15000;
        // 处理完请求，返回内容
        String resultJson = gson.toJson(ret);
        if (resultJson.length() <= maxLength) {
            log.info("RESULT : {}", resultJson);
        } else {
            log.info("RESULT : {} (truncated)...", resultJson.substring(0, maxLength));
        }
    }


    @Around("controllerLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        // ob 为方法的返回值
        Object ob = pjp.proceed();
        log.info("耗时 :{}毫秒 ", (System.currentTimeMillis() - startTime));
        return ob;
    }

}
