package com.example.demo.http;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //String requestUUID = UUID.randomUUID().toString();
        String requestUUID = MDC.get("requestUUID");
        if (requestUUID == null || "".equals(requestUUID)) {
            String uid = ObjectId.get().toHexString();
            MDC.put("requestUUID", uid);
            response.setHeader("Request-UUID", uid);
        }

        // 只有返回true才会继续向下执行，返回false取消当前请求
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 线程结束后需要清除,否则当前线程会一直占用这个requestId值
        MDC.remove("requestUUID");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 整个请求线程结束后需要清除,否则当前线程会一直占用这个requestId值
        MDC.clear();
    }
}
