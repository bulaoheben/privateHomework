package com.example.auto_warehouse.controller;

import com.example.auto_warehouse.bean.User;
import com.example.auto_warehouse.service.UserService;
import com.example.auto_warehouse.util.JsonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    //登录部分及相关函数，还需要根据用户type分别再写个函数/或者加个参数type判断（未完成）
    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public JsonResult<User> login(@RequestBody Map<String,String> map){
        String username = map.get("username");
        String password = map.get("password");
        System.out.println("进到这里");
        return userService.login(username,password);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/create")
    public JsonResult<Integer> create(@RequestBody Map<String,String> map){
        String username = map.get("username");
        String password = map.get("password");
        String phone = map.get("phone");
        String email = map.get("email");
        return userService.createAccount(username,password,phone,email);
    }

}
