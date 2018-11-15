package com.mozi.config.securityconfig;

import com.mozi.constant.CommCode;
import com.mozi.entity.Permission;
import com.mozi.entity.RoleEntity;
import com.mozi.entity.UserSecurityEntity;
import com.mozi.exception.MoziException;
import com.mozi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by MOZi on 2018/11/11.
 */
@Component("permissionControl")
public class PermissionControl {

    @Autowired
    UserMapper userMapper;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) throws MoziException {

        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;

        if(principal instanceof UserDetails) {

            String username = ((UserDetails) principal).getUsername();


            UserSecurityEntity user = userMapper.findByUsername(username);

            //根据用户名获取所有的角色
            List<RoleEntity> roles = userMapper.findUserRolesByUser(user);
            //暂定用户只有一个角色
            RoleEntity role= roles.get(0);

            //根据角色获取所有的url权限
            List<Permission> permissions = userMapper.findURLbyRole(role);

            Set<String> urls = new HashSet<>();

            for (Permission p :permissions){
                urls.add(p.getUrl());
            }

            for (String url : urls) {
                // 判断当前url是否有权限
                if(antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }

//            if (!hasPermission){
//                throw new MoziException(CommCode.FORBIDDEN_CODE,"禁止访问！");
//            }
        }
        return hasPermission;
    }

}
