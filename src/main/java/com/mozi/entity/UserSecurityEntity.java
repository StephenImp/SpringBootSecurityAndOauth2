package com.mozi.entity;

import com.mozi.annotation.ExcelTitle;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * UserDetails是SpringSecurity验证框架内部提供的用户验证接口（我们下面需要用到UserEntity来完成自定义用户认证功能），
 * 我们需要实现getAuthorities方法内容，将我们定义的角色列表添加到授权的列表内。
 */
public class UserSecurityEntity implements Serializable,UserDetails

{
    private Long id;
    private String username;
    private String password;
    private String email;


    private List<RoleEntity> roles;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<RoleEntity> roles = getRoles();
        for(RoleEntity role : roles)
        {
            auths.add(new SimpleGrantedAuthority(role.getFlag()));
        }
        return auths;
    }

    @Override
    @ExcelTitle(value = "密码",order = 3)
    public String getPassword() {
        return password;
    }

    @Override
    @ExcelTitle(value = "账号",order = 2)
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    @ExcelTitle(value = "序号",order = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
