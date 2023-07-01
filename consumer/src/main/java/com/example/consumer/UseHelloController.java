package com.example.consumer;

import org.example.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.io.UnsupportedEncodingException;
import java.net.URI;

@RestController
public class UseHelloController {
    @Autowired
    DiscoveryClient discoveryClient;//以前需要注解，现在不用了。直接Autowired
    @Autowired
    @Qualifier("restTemplateOne")//多个RestTemplate 的@Bean时，需要此注解
    RestTemplate restTemplateOne;
    @Autowired
    @Qualifier("restTemplateTwo")//多个@Bean RestTemplate
    RestTemplate restTemplateTwo;
    @GetMapping("/consumehello")
    public String consumehello(){
        //http处理，用RestTemplate代替。节省了非常多的代码
        String s = restTemplateTwo.getForObject("http://provider/hello", String.class);//1.请求地址 2.返回的数据类型
        return s;
    }
    @GetMapping("/consumehello2")
    public  String consumehello2() throws UnsupportedEncodingException {
        //第三个参数可变长度，name={1}即代表传入可变参数中的第1个
        String s1 = restTemplateTwo.getForObject("http://provider/hello2?name={1}", String.class,"Param1");

        System.out.println("getForObject->String:"+s1);
        ResponseEntity<String> responseEntity = restTemplateTwo.getForEntity("http://provider/hello2", String.class, "Param");
        String body = responseEntity.getBody();
        System.out.println("getForEntity->ResponseEntity<String>.body:"+body);
        return "aaaa";
    }
    @GetMapping("/posthello1")
    public void postHello1(){
        MultiValueMap<String,Object> map=new LinkedMultiValueMap<>();
        map.add("username","javaboy");
        map.add("password","123");
        map.add("age",99);
        User user = restTemplateTwo.postForObject("http://provider/user1", map, User.class);//key-value
        System.out.println(user);

        user.setAge(98);user.setUsername("boy");
        User user1 = restTemplateTwo.postForObject("http://provider/user2", user, User.class);//Json形式
        System.out.println(user1);
    }
    @GetMapping("/posthello2")
    public void  postHello2(){
        MultiValueMap<String,Object> map=new LinkedMultiValueMap<>();
        map.add("username","javaboy");
        map.add("password","123");
        map.add("age",99);
        URI uri = restTemplateTwo.postForLocation("http://provider/register", map);
        System.out.println(uri);
        String s = restTemplateOne.getForObject(uri, String.class);
    }
    @GetMapping("/deletehello")
    public void deletehello(){
        restTemplateTwo.delete("http://provider/user1?id={1}",99);
        restTemplateTwo.delete("http://provider/user2/{1}",99);
    }
}
