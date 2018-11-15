package com.mozi.mapper;

import com.mozi.entity.Permission;
import com.mozi.entity.RoleEntity;
import com.mozi.entity.UserSecurityEntity;

import java.util.List;
import java.util.Set;

/**
 * Created by MOZi on 2018/11/11.
 */
public interface UserMapper {

     UserSecurityEntity findByUsername(String username);

     List<RoleEntity> findUserRolesByUser(UserSecurityEntity user);

     List<Permission> findURLbyRole(RoleEntity role);
}
