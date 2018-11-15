package com.mozi.service.impl;

import com.mozi.entity.UserSecurityEntity;
import com.mozi.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by MOZi on 2018/11/11.
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserSecurityEntity findByUsername(String username) {
        return null;
    }
}
