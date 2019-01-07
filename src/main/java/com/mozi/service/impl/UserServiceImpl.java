package com.mozi.service.impl;

import com.mozi.entity.UserSecurityEntity;
import com.mozi.mapper.UserMapper;
import com.mozi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MOZi on 2018/11/11.
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public UserSecurityEntity findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
