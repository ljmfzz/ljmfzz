package com.imooc.mall.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import org.slf4j.Logger;




@Aspect
@Component
public class WebLogAspect {
    private final Logger log =  LoggerFactory.getLogger(WebLogAspect.class);
    @Pointcut("execution(public* com.imooc.mall.controller.*.*(..)))")
    public void webLog(){

    }
    @Before("webLog()")
    public void doBfore(JoinPoint joinPoint){
        ServletRequestAttributes attributes=
                (ServletRequestAttributes)RequestContextHolder
                        .getRequestAttributes();
         HttpServletRequest request=attributes.getRequest();
         log.info("url:"+request.getRequestURI().toString());
        log.info("HTTP_METHOD:"+request.getMethod());
        log.info("IP:"+request.getRemoteAddr());
        log.info("CLASS_METH:"+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getDeclaringTypeName());
        log.info("ARGS:"+ Arrays.toString(joinPoint.getArgs()));


    }
    @AfterReturning(returning = "res",pointcut = "webLog()")
    public void doAfterReturning(Object res) throws JsonProcessingException {
        log.info("RESPONSE:"+new ObjectMapper().writeValueAsString(res));
    }

}
