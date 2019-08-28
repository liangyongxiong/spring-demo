package com.example.demo.http;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class WebRequestAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * com.example.demo.controller.*.* (..))")
    private void webRequest(){}

    @Before("webRequest()")
    private void doBefore(JoinPoint joinPoint) throws Throwable {
        // 获取 request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 打印 url 信息
        logger.info("[url] {} {}", request.getMethod(), request.getRequestURL().toString());

        // 打印 handler 信息
        MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
        logger.info("[handler] {}.{}", methodSignature.getDeclaringTypeName(), methodSignature.getName());

        // 打印 header 信息
        Map headers = new HashMap<String, String>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            headers.put(name, request.getHeader(name));
        }
        logger.info("[headers] {}", headers.toString());

        // 打印 querystring 信息
        Map params = new HashMap<String, Object>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            params.put(name, request.getParameter(name));
        }
        if (params.keySet().size() > 0) {
            logger.info("[params] {}", params);
        }

        // 打印 body 信息
        Annotation[][] annotationMatrix = methodSignature.getMethod().getParameterAnnotations();
        Object body = null;
        int index = -1;
        for (Annotation[] annotations : annotationMatrix) {
            index = index + 1;
            if (body != null) {
                break;
            }
            for (Annotation annotation : annotations) {
                if (annotation instanceof RequestBody) {
                    body = joinPoint.getArgs()[index];
                    break;
                }
            }
        }
        if (body != null) {
            if (body instanceof String) {
                logger.info("[body] {}", body);
            } else {
                logger.info("[body] {}", JSONObject.toJSONString(body, SerializerFeature.WriteMapNullValue));
            }
        }
    }

    @AfterReturning(returning = "response", pointcut = "webRequest()")
    public void doAfterReturning(JoinPoint joinPoint, Object response) throws Throwable {
        // 打印 response 信息
        logger.info("[return] {}", JSONObject.toJSONString(response, SerializerFeature.WriteMapNullValue));
    }
}
