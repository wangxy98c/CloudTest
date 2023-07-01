package com.example.stream;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @GetMapping("/hello")
    public void hello(){
        rabbitTemplate.convertAndSend("input-in-0.destination1.someGroup","helloController");
    }
}
