package me.jiangyu.may.web.controller.log4j;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * log4j 集群测试
 * Created by jiangyukun on 2015/5/4.
 */
@Controller
@RequestMapping("/log4j")
public class Log4jController {
    private static Logger logger = Logger.getLogger(Log4jController.class);

    @RequestMapping("/test")
    public void test() {

    }

    public static void main(String[] args) {
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");
    }
}
