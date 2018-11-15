package com.mozi.config.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by MOZi on 2018/11/15.
 *
 * 注销,跳转到这里来
 */

@Controller
public class LogoutHandler {

    /**
     * 重定向 策略
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @RequestMapping(value = "/authentication/logout")

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String redirectUrl = "/authentication/require";

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        redirectStrategy.sendRedirect(request,response,redirectUrl);
    }

}
