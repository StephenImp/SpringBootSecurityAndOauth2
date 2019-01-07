package com.mozi.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOZi on 2018/11/15.
 */
@Controller
public class SecurityController extends BaseController{

    @PostMapping("/user/login")
    public User login(String userName,String password){

        //根据用户名密码去查询用户信息

        List<GrantedAuthority> authorities = new ArrayList<>();

        //员工密码进行加密处理
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        System.out.println(hashedPassword);

        //返回SpringSecurity需要的用户对象
        return new User(userName,hashedPassword, authorities);

    }
}
