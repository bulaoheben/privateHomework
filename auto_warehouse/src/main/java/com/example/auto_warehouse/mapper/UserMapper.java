package com.example.auto_warehouse.mapper;

import com.example.auto_warehouse.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findByname(String username);

    void createAccount(String username, String password,String phone,String email,int id);

    List<Integer> getId();
}
