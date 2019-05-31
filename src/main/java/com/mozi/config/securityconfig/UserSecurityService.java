package com.mozi.config.securityconfig;

import com.mozi.entity.RoleEntity;
import com.mozi.entity.UserSecurityEntity;
import com.mozi.mapper.UserMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个service就是专门用来校验用户的账号密码是否正确的，并且会为密码进行加密
 */
public class UserSecurityService implements UserDetailsService
{
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    UserMapper userMapper;

    @Override
    //重写loadUserByUsername 方法获得 userdetails 类型用户
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据用户名查找用户信息
        UserSecurityEntity user = null;


        //账号登录
         user = userMapper.findByUsername(username);


        if(user == null)
        {
            throw new UsernameNotFoundException("未查询到用户："+username+"信息！");
        }

        //根据用户信息查到他的权限
        List<RoleEntity> roles =  userMapper.findUserRolesByUser(user);

        user.setRoles(roles);

        List<GrantedAuthority> authorities = new ArrayList<>();

        //把用户的角色添加到authorities中
        for(RoleEntity role:user.getRoles())
        {
            authorities.add(new SimpleGrantedAuthority(role.getFlag()));
            logger.info("flag: " + role.getFlag());
        }

        //返回SpringSecurity需要的用户对象
        return new User(user.getUsername(),user.getPassword(), authorities);
    }
}
