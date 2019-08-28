package com.example.demo.http;

import com.example.demo.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice("com.example.demo.controller")
public class ControllerExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String httpRequestMethodNotSupportedHandler(HttpServletResponse response) {
        return "method not allow";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public String noHandlerFoundHandler(HttpServletResponse response) {
        response.setStatus(200);
        return "not found";
    }

    @ResponseBody
    @ExceptionHandler(BizException.class)
    public JsonResponse bizExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        BizException be = (BizException) e;
        logger.error(be.getMsg());
        return JsonResponse.builder().err(1).msg(be.getMsg()).build();
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        String stackTrace = getExceptionStackTrace(e);
        logger.error(stackTrace);

        return JsonResponse.builder().err(1).msg("unknown error").build();
    }

    private String getExceptionStackTrace(Exception e) {
        StringBuilder sb = new StringBuilder(e.getClass().getName());
        String message = e.getMessage();
        if (message != null) {
            sb.append(": " + message);
        }

        sb.append("\n");
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            sb.append("at ");
            sb.append(stackTraceElement.getClassName());
            sb.append(".");
            sb.append(stackTraceElement.getMethodName());
            sb.append("(");
            sb.append(stackTraceElement.getFileName());
            sb.append(":");
            sb.append(stackTraceElement.getLineNumber());
            sb.append(")\n");
        }

        return sb.toString();
    }
}
