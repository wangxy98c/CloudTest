package com.example.provider;

import org.example.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller//不能是RestController：否则会认为是字符串，而不是重定向了
public class RegisterController {
    @PostMapping("/register")
    public String register(User user){
        return "redirect:/loginPage?username="+user.getUsername();
    }
    @GetMapping("/loginPage")
    @ResponseBody//因为不是RestController，需要注解。返回字串
    public String loginPage(String username){
        return "loginPage:"+username;
    }
}
