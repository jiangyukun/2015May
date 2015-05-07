package me.jiangyu.may.web.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller 增强
 * Created by jiangyukun on 2015/5/7.
 */
@ControllerAdvice
public class ResponseBodyAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ResponseBodyAdvice.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String exceptionHandler(Exception e) {
        logger.error(e.getMessage());
        return e.getMessage();
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        logger.debug(webDataBinder.toString());
    }
}
