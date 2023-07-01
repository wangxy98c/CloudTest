package com.example.openfeign;

import org.example.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

@Component
@RequestMapping("/suibianxie")
public class HelloServiceFallback implements HelloService{
    @Override
    public String hello() {
        return "error";
    }

    @Override
    public String hello2(String name) {
        return null;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public void deleteUserById(Integer id) {

    }

    @Override
    public void getUserByName(String name) throws UnsupportedEncodingException {

    }
}
