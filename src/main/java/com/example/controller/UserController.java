package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pojo.Result;
import com.example.pojo.User;
import com.example.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String username,String password){
        //查询用户
        User u = userService.findByUserName(username);
        // System.out.println(u);
        if (u==null){
            //用户名没有占用
            //注册
            userService.register(username,password);
            return Result.success();
        }else{
            //用户名被占用
            return Result.error("用户名已被占用");
        }
    }
}
