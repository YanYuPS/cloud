package com.zy.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 客户端
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class}) //取消数据库配置
@EnableDiscoveryClient //开启注册中心eureka、gateway
@EnableCircuitBreaker //开启熔断器hystrix

@EnableFeignClients //开启feign

//@SpringCloudApplication
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class);
    }

    @Bean
    @LoadBalanced //使用负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
