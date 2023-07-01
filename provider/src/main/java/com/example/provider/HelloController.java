package com.example.provider;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.example.IUserService;
import org.example.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

@RestController
public class HelloController implements IUserService {
    @Value("${server.port}")
    Integer port;
    @Override
    @RateLimiter(name = "backendA")
    public String hello(){
        String s="hello:port>>>>>"+port;
        System.out.println(">>>>>>>>>>  "+new Date());
        return s;
    }
    @Override
    public String hello2(String name){
        return "hello"+name;
    }
    @Override
    public User addUser(@RequestBody User user){//以JSON形式传递需要注解
        return user;
    }
    @Override
    public void deleteUserById(@PathVariable Integer id){
        System.out.println("delete>>>>>>>"+id);
    }
    @Override
    public void getUserByName(@RequestHeader("name") String name) throws UnsupportedEncodingException {

        System.out.println("RequestHeader->>>>>>"+URLDecoder.decode(name,"UTF-8"));
    }

    @DeleteMapping("/user1")
    public void deleteUser1(Integer id){
        System.out.println(id);
    }
    @PostMapping("/user1")
    public User addUser1(User user){//key-value形式不需要
        return user;
    }
}

