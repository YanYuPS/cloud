package com.zy.manager.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zy.manager.config.ServiceIdProperties;
import com.zy.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/manager")
@Slf4j
@DefaultProperties(defaultFallback = "defaultFallback")
public class ManagerController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ServiceIdProperties properties;

    @RequestMapping("/{id}")
    @HystrixCommand(fallbackMethod = "queryByIdFallback") //熔断器--服务降级逻辑
//    @HystrixCommand //熔断器--服务降级逻辑--默认
    public String queryById(@PathVariable Long id){
        //获取配置文件中的服务Id
        String serviceId=properties.getUserService();

        String url="http://"+serviceId+"/users/"+id; //不加port，使用负载均衡
        //获取eureka中注册的user-service实例列表
//        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
//        ServiceInstance serviceInstance = instances.get(0);
//        url="http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/user/"+id;

        System.out.println("serviceId:"+serviceId);
        System.out.println("url:"+url);

        User user = restTemplate.getForObject(url, User.class);
        return user.toString();
    }


    //服务降级---要和正常方法 参数一样，返回值声明
    public String queryByIdFallback(Long id){
        log.error("查询失败，id:", id);
        return "网络拥挤";
    }
    //默认
    public String defaultFallback(){
        return "网络拥挤";
    }


}
