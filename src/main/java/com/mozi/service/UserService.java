package com.mozi.service;

import com.mozi.entity.UserSecurityEntity;

/**
 * Created by MOZi on 2018/11/11.
 */
public interface UserService {

    /**
     * 根据用户名查找用户信息
     * @param username
     * @return
     */
     UserSecurityEntity findByUsername(String username);
}
