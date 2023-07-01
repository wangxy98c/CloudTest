package com.example.resilience4j;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
//@Retry(name = "backendA")
//@CircuitBreaker(name="backendA",fallbackMethod = "errorfunction")
@RateLimiter(name = "backendA")
public class HelloService {
    @Autowired
    RestTemplate restTemplate;
    public String hello(){
        for (int i=0;i<200;i++) {
            restTemplate.getForObject("http://localhost:1113/hello",String.class);
        }

        return "success";
    }
    public String errorfunction(Throwable t){//这里更应该是具体异常，但此处用了异常大类
        return "error";
    }
}
