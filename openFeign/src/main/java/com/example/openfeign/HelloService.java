package com.example.openfeign;

import feign.Param;
import org.example.IUserService;
import org.example.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "provider",fallbackFactory = HelloServiceFallbackFactory.class)//调用provider中的服务，故而和provider绑定
public interface HelloService extends IUserService {
}
