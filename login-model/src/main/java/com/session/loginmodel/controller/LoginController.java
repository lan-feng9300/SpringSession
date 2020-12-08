package com.session.loginmodel.controller;

import com.session.loginmodel.entity.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Api(value = "Login",description = "api")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private StringRedisTemplate  redisTemplate;

    @ApiOperation(value = "/login")
    @PostMapping("/loginTest")
    public void loginTest(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        UserInfo user = new UserInfo("李四", "12345");
        //redisTemplate.opsForValue().set("user",user.toString());

        /**
         *  1: 添加 springSession 的依赖
         *  2: 配置 application.yml spring.session.store.type=redis
         *  3: @EnableRedisHttpSession
         */
        session.setAttribute("user",user);
        response.addCookie(new Cookie("JSESSIONID","session"));
        Object user1 = session.getAttribute("user");
        System.out.println(user1);

    }
}
