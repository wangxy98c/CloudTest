package org.example;

import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

public interface IUserService {
    //这部分来源于openfeign的接口（因为是为了统一
    @GetMapping("/hello")// 无参数形式
        //这里的hello对应的时provider中的hello方法，即provider/hello()。不是调用此方法的地址！！
        //另外使用的是GetMapping则对应了GetForObject
    String hello();//一个方法名而已，不用对应
    @GetMapping("/hello2")//   hello2(name)
    String hello2(@RequestParam("name") String name);// 绑定参数name
    @PostMapping("/user2") // addUser2(@RequestBody User user)
    User addUser(@RequestBody User user);//Json形式不用绑定参数
    @DeleteMapping("/user2/{id}")// deleteUser2(@PathVariable Integer id)
    void deleteUserById(@PathVariable("id") Integer id);//绑定
    @GetMapping("/user3") //getUserByName(@RequestHeader String name)
    void getUserByName(@RequestHeader("name") String name) throws UnsupportedEncodingException;
}
