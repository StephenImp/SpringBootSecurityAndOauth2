package com.mozi.config.securityconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mozi.constant.CommCode;
import com.mozi.entity.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by MOZi on 2018/11/15.
 *
 * 自定义登录成功处理器，如果登录认证成功，会运行这个类。
 */
@Component
@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    //public static final String RETURN_TYPE = "html"; // 登录成功时，用来判断是返回json数据还是跳转html页面

    @Autowired
    private ObjectMapper objectMapper;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


        log.info("登录成功");
        log.info("username=>" + request.getParameter("username"));
//        if(RETURN_TYPE.equals("html")) {
//            redirectStrategy.sendRedirect(request, response, "/user/index");
//        } else {
//            Map<String, Object> map = new HashMap<>();
//            map.put("code","0");
//            map.put("msg","登录成功");
//            map.put("data",authentication);
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().write(objectMapper.writeValueAsString(map));
//        }

        ResultModel resultModel = new ResultModel();
        resultModel.setData(authentication);
        resultModel.setStatus(CommCode.SUCCESS_CODE);
        resultModel.setMsg("登录成功!");

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(resultModel));


    }

}
