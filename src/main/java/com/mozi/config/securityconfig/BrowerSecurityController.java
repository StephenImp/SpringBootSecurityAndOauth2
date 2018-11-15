package com.mozi.config.securityconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mozi.entity.ResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * Created by MOZi on 2018/11/15.
 *
 * 请求时，没有登录，跳转到这里来
 *
 * MMP，终于他妈解决了。
 *
 * 判断json/html 请求 返回不同的结果
 * @ 注解@ResponseStatus ：响应状态码 UNAUTHORIZED(401, "Unauthorized")
 * 响应状态码 UNAUTHORIZED(401, "Unauthorized")
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
@RestController
public class BrowerSecurityController {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 重定向 策略
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 把当前的请求缓存到 session 里去
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * 注入 Security 属性类配置
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 当需要身份认证时 跳转到这里
     */
    @RequestMapping("/authentication/require")
    public void requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //拿到请求对象
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null){
            //获取 跳转url
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求是:"+targetUrl);

            //判断 targetUrl 是不是 .html　结尾, 如果是：跳转到登录页(返回view)
            if (StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                String redirectUrl = "login";
                redirectStrategy.sendRedirect(request,response,redirectUrl);
            }

        }

        ResultModel resultModel = new ResultModel();
        resultModel.setStatus(111);
        resultModel.setMsg("访问的服务需要身份认证，请引导用户到登录页");
        //如果不是，返回一个json 字符串

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(resultModel));
    }

}
