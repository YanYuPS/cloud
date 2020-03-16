package com.zy.user.controller;

import com.zy.pojo.User;
import com.zy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RefreshScope //刷新配置
public class UserController {
    @Autowired
    private UserService userService;

    @Value("${test.name}")
    private String name;


    @RequestMapping("/{id}")
    public User queryById(@PathVariable Long id){
        System.out.println("test.name: "+name); //用来验证bus--rsbbitmq
        System.out.println("user-service: UserController");
        return userService.queryById(id);
    }
}
