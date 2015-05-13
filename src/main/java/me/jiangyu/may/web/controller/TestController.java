package me.jiangyu.may.web.controller;

import me.jiangyu.may.entity.User;
import me.jiangyu.may.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试控制器
 * Created by jiangyukun on 2015/5/4.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("test1")
    @ResponseBody
    public void getCookie(HttpServletRequest request, HttpServletResponse response) {
        logger.info("handle test1");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                logger.info(cookie.getName() + ": " + cookie.getValue());
            }
        }
    }

    @RequestMapping("setCookie")
    public void setCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("a", "a");
        cookie.setPath("/");
        cookie.setMaxAge(10000);
        response.addCookie(cookie);
        logger.info("handle setCookie");
    }

    @RequestMapping("threadTest")
    public void threadTest() throws Exception {
        logger.info("@" + Integer.toHexString(hashCode()) + " " + Thread.currentThread().getName() + " start");
        Thread.sleep(2000);
        logger.info(Thread.currentThread().getName() + " is ok");
    }

    @RequestMapping("throwException")
    public String throwException() throws Exception {
        int sleepTime = (int) (Math.random() * 2000) + 1;
        Thread.sleep(sleepTime);
        throw new RuntimeException("throwException");
    }

    @RequestMapping("throwWait2000")
    public String throwWait1000() throws Exception {
        Thread.sleep(2000);
        throw new RuntimeException("throwException");
    }

    @RequestMapping("addUser")
    public void addUser() {
        User user = new User();
        for (int i = 0; i < 100; i++) {
            user.setId(String.valueOf(i));
            user.setName("call " + i);
            userService.save(user);
        }
    }

    @RequestMapping("threadPool")
    public void threadPool() throws Exception{
        userService.lockTable();
    }
}
