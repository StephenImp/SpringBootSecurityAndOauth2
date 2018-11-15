package com.mozi.config.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)// 控制权限注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;//没有权限返回自定义数据
    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;//登录成功返回自定义数据
    @Autowired
    private MyAuthenticationFailHandler authenticationFailHandler;//登录失败返回自定义数据

    //完成自定义认证实体注入
    @Bean
    UserDetailsService userService() {
        UserSecurityService userService = new UserSecurityService();
        return userService;
    }

    /**
     * 设置用户密码的加密方式为BCrypt加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 当前版本5新增支持加密方式：
     * bcrypt - BCryptPasswordEncoder (Also used for encoding)
     * ldap - LdapShaPasswordEncoder
     * MD4 - Md4PasswordEncoder
     * MD5 - new MessageDigestPasswordEncoder("MD5")
     * noop - NoOpPasswordEncoder
     * pbkdf2 - Pbkdf2PasswordEncoder
     * scrypt - SCryptPasswordEncoder
     * SHA-1 - new MessageDigestPasswordEncoder("SHA-1")
     * SHA-256 - new MessageDigestPasswordEncoder("SHA-256")
     * sha256 - StandardPasswordEncoder
     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService()).passwordEncoder(passwordEncoder());
    }

    /**
     * configureGlobal(AuthenticationManagerBuilder auth) 方法，
     * 在内存中创建了一个用户，该用户的名称为username，密码为password，用户角色为USER。
     */
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                //.passwordEncoder(new MyPasswordEncoder())//在此处应用自定义PasswordEncoder
//                .withUser("admin")
//                .password("123456")
//                .roles("USER");
//    }

    /**
     * 通过 authorizeRequests() 定义哪些URL需要被保护、哪些不需要被保护。
     * 例如以上代码指定了 / 和 /home 不需要任何认证就可以访问，其他的路径都必须通过身份验证。
     * <p>
     * 通过 formLogin() 定义当需要用户登录时候，转到的登录页面。
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/", "/login","/authentication/logout").permitAll()//这些接口所有人都能访问
                .anyRequest().authenticated(); // 任何请求,登录后可以访问

        //.antMatchers(HttpMethod.POST, "/user").hasRole("ADMIN");// 此时POST方法会需要角色权限
        //.antMatchers("/user/**").hasAnyRole("USER") // 需要具有ROLE_USER角色才能访问
        //.antMatchers("/admin/**").hasAnyRole("ADMIN") // 需要具有ROLE_ADMIN角色才能访问
        //.permitAll()
        //.authenticated();//其他所有路径都需要权限校验
        //.permitAll();

        // 开启默认登录页面
        // http.formLogin();

        //自定义登录页面
        http.csrf().disable().//默认开启，这里先显式关闭
                formLogin()
                .loginPage("/authentication/require") //表单登录页面地址 *
                //.loginPage("/login") //表单登录页面地址
                //.loginProcessingUrl("/authentication/require")//form表单POST请求url提交地址，默认为/login
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailHandler)
                .permitAll();//其他接口都需要进入登录页面

        /**
         * 这里是权限控制，亲测有效，但现在暂时用不到
         */
        http.authorizeRequests()
                .anyRequest()
                .access("@permissionControl.hasPermission(request, authentication)");//权限控制

        http.csrf().disable().
                logout()
                .logoutUrl("/logout")//触发注销操作的url，默认是/logout。如果开启了CSRF保护(默认开启),那么请求必须是POST方式。
                //.logoutSuccessUrl("/authentication/require")//注销操作发生后重定向到的url，默认为 /login?logout。
                //.logoutSuccessHandler(logoutSuccessHandler)//让你指定自定义的 LogoutSuccessHandler。如果指定了,logoutSuccessUrl() 将会被忽略。
                .invalidateHttpSession(true);//指定在注销的时候是否销毁 HttpSession 。默认为True。
                //.addLogoutHandler(logoutHandler)//添加一个 LogoutHandler。默认情况下， SecurityContextLogoutHandler 被作为最后一个 LogoutHandler 。
                //.deleteCookies(cookieNamesToClear)//允许指定当注销成功时要移除的cookie的名称。这是显式店家 CookieClearingLogoutHandler 的一种快捷处理方式。


                //.and()
                //.exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler);//权限控制后，自定义403返回数据


    }


//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        //解决静态资源被拦截的问题
//        web.ignoring().antMatchers("/css/**");
//    }

}
