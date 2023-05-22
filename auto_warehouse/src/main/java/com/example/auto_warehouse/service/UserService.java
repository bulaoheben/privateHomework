package com.example.auto_warehouse.service;

import com.example.auto_warehouse.bean.JwtUtility;
import com.example.auto_warehouse.bean.User;
import com.example.auto_warehouse.mapper.UserMapper;
import com.example.auto_warehouse.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    private JwtUtility jwtutility=new JwtUtility();

    public List<Integer> getId(){
        return userMapper.getId();
    }
    public User findByname(String username){
        return userMapper.findByname(username);
    }

    //登录
    public JsonResult<User> login(String username, String password){
        // 检查该用户是否存在
        User user = findByname(username);
        if (user!=null){
            if(user.getPassword().equals(password)){
                String token = jwtutility.generateToken(username);
                user.setToken(token);
                return new JsonResult<>(user,"登陆成功！");
            }else {
                return new JsonResult<>("0","密码错误!");
            }
        }else {
            return new JsonResult<>("0","账号错误，用户不存在!");
        }
    }

    public JsonResult<Integer> createAccount(String username, String password,String phone,String email){
        // 检查该用户是否存在
        User user = findByname(username);
        if (user!=null){
            return new JsonResult<>(0,"该账户不可注册，请尝试输入其他用户名");
        }else {
            List<Integer> idlist= getId();
            int newid=generateId(idlist);
            userMapper.createAccount(username,password,phone,email,newid);
            return new JsonResult<>(1,"用户创建成功");
        }
    }

    public int generateId(List<Integer> list){
        // 新建随机数生成器对象
        Random rand = new Random();
        int randomInt;
        do {
            // 循环生成随机数，直到生成一个不在 List 范围内的整数
            randomInt = rand.nextInt();
        } while (list.contains(randomInt));
        return randomInt;
    }
}
