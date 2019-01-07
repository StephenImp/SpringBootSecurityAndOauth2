package com.mozi.config.securityconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mozi.constant.CommCode;
import com.mozi.entity.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by MOZi on 2018/11/15.
 * 自定义登录失败处理器，如果登录认证失败，会跳到这个类上来处理。
 */

@Component
@Slf4j
public class MyAuthenticationFailHandler implements AuthenticationFailureHandler  {

    //public static final String RETURN_TYPE = "html"; // 登录失败时，用来判断是返回json数据还是跳转html页面

    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("登录失败:" + exception.getMessage());
        log.info("username=>" + request.getParameter("username"));

//        if (RETURN_TYPE.equals("html")) {
//            redirectStrategy.sendRedirect(request, response, "/login/index?error=true");
//        } else {
//            Map<String, Object> map = new HashMap<>();
//            map.put("code","1002");
//            map.put("msg","登录失败");
//            map.put("data",exception.getMessage());
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().write(objectMapper.writeValueAsString(map));
//        }

        ResultModel resultModel = new ResultModel();
        resultModel.setData(exception.getMessage());
        resultModel.setStatus(CommCode.FAILURE_CODE);
        resultModel.setMsg("登录失败!");

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(resultModel));

    }
}
