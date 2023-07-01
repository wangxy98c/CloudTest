package com.example.openfeign;

import org.example.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
public class HelloController {
    @Autowired
    HelloService helloService;
    @GetMapping("/hello")
    public  String hello() throws UnsupportedEncodingException {
        String s = helloService.hello2("中文文本");
        System.out.println(s);

        User user=new User();user.setUsername("中文名");user.setPassword("123");user.setAge(12);
        User user1 = helloService.addUser(user);
        System.out.println(user1);

        helloService.deleteUserById(1);

        helloService.getUserByName(URLEncoder.encode("中文文本","UTF-8"));
        return s;
    }

}
