package com.example.openfeign;

import org.example.User;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.io.UnsupportedEncodingException;

public class HelloServiceFallbackFactory implements FallbackFactory<HelloService> {
    @Override
    public HelloService create(Throwable cause) {
        return new HelloService() {
            @Override
            public String hello() {
                return "error";
            }

            @Override
            public String hello2(String name) {
                return "error";
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

        };
    }
}
