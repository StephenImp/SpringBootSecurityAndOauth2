package com.mozi.config.securityconfig;

import com.mozi.interceptor.TestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MVCConfig extends WebMvcConfigurerAdapter{

    /**
     *   下面我们添加一个名叫MVCConfig配置类继承WebMvcConfigurerAdapter类，
     *   重写addViewControllers()方法添加路径访问，
     *   可以通过Get形式的/login访问到我们的login.jsp
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/hello").setViewName("hello");
    }


    /**
     * 拦截器配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**");
    }


    /**
     * 配置请求视图映射
     * @return
     */
//    @Bean
//    public InternalResourceViewResolver resourceViewResolver()
//    {
//        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
//        //请求视图文件的前缀地址
//        internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
//        //请求视图文件的后缀
//        internalResourceViewResolver.setSuffix(".jsp");
//        return internalResourceViewResolver;
//    }

    /**
     * 视图配置
     * @param registry
     */
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        super.configureViewResolvers(registry);
//        registry.viewResolver(resourceViewResolver());
//    /*registry.jsp("/WEB-INF/jsp/",".jsp");*/
//    }

    /**
     * 消息内容转换配置
     * 配置fastJson返回json转换
     *
     * 返回的内容在这里处理
     * @param converters
     */
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        //调用父类的配置
//        super.configureMessageConverters(converters);
//        //创建fastJson消息转换器
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        //创建配置类
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        //修改配置返回内容的过滤
//        fastJsonConfig.setSerializerFeatures(
//                SerializerFeature.DisableCircularReferenceDetect,
//                SerializerFeature.WriteMapNullValue,
//                SerializerFeature.WriteNullStringAsEmpty
//        );
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        //将fastjson添加到视图消息转换器列表内
//        converters.add(fastConverter);
//    }
}
