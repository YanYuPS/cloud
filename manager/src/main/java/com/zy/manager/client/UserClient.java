package com.zy.manager.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 	Feign会通过动态代理，帮我们生成实现类
 */
@FeignClient("user-service") //服务名
public interface UserClient {

    @RequestMapping("/users/{id}")
    String queryById(@PathVariable Long id);
}
