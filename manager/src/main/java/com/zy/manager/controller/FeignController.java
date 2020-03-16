package com.zy.manager.controller;

import com.zy.manager.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
public class FeignController {
    @Autowired
    private UserClient userClient;

    @RequestMapping("/{id}")
    public String queryById(@PathVariable Long id){
        return userClient.queryById(id);
    }
}
