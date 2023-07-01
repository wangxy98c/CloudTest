package com.example.consulconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
    @Autowired
    LoadBalancerClient loadBalancerClient;//用于查找选择服务
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    @Qualifier("restTemplateBalanced")
    RestTemplate restTemplatebalanced;
    @GetMapping("/hello")
    public String hello(){
        //负载均衡，它会自动选择一个具体的服务并返回。可以从中拿到服务的uri等信息
        ServiceInstance instance = loadBalancerClient.choose("consul-provider");
        System.out.println(instance.getUri());
        String s = restTemplate.getForObject(instance.getUri()+"/hello", String.class);
        return s;
    }
    @GetMapping("/hello2")
    public String hello2(){
        String s = restTemplatebalanced.getForObject("http://consul-provider/hello", String.class);
        return s;
    }
}
