package com.mozi.config.securityconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mozi.constant.CommCode;
import com.mozi.entity.ResultModel;
import com.mozi.entity.UserSecurityEntity;
import com.mozi.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
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
 * <p>
 * 自定义登录成功处理器，如果登录认证成功，会运行这个类。
 */
@Component
@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    //public static final String RETURN_TYPE = "html"; // 登录成功时，用来判断是返回json数据还是跳转html页面

    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    UserMapper userMapper;

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

//        String username =  request.getParameter("username");
//
//        //根据用户名查找用户信息
//        UserSecurityEntity user = null;
//
//        //包含#号的是员工端
//        if (username.contains("#")){
//            int i = username.indexOf("#");
//            String usernameStr = username.substring(0,i);
//
//            //邮箱登录
//            if (username.contains("@")){
//                user = userMapper.findByUserEmail(usernameStr);
//            }else {//账号登录
//                user = userMapper.findByUsername(usernameStr);
//            }
//
//        }else{//人事端
//            //邮箱登录
//            if (username.contains("@")){
//                user = userMapper.findByUserEmail(username);
//            }else {//账号登录
//                user = userMapper.findByUsername(username);
//            }
//        }


//        //1.从HttpServletRequest中获取SecurityContextImpl对象
//        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
//        //2.从SecurityContextImpl中获取Authentication对象
//        authentication = securityContextImpl.getAuthentication();
//        //3.初始化UsernamePasswordAuthenticationToken实例 ，这里的参数user就是我们要更新的用户信息
//        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, authentication.getCredentials());
//        auth.setDetails(authentication.getDetails());
//        //4.重新设置SecurityContextImpl对象的Authentication
//        securityContextImpl.setAuthentication(auth);


        ResultModel resultModel = new ResultModel();
        resultModel.setData(authentication);
        resultModel.setStatus(CommCode.SUCCESS_CODE);
        resultModel.setMsg("登录成功!");

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(resultModel));


    }

}
