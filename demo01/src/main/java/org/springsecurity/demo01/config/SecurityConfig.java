package org.springsecurity.demo01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springsecurity.demo01.security.*;
import org.springsecurity.demo01.service.SysUserService;

/**
 * 安全配置
 *
 * @author wangtongzhou 
 * @since 2023-03-03 21:27
 */
//Spring Security框架开启
@EnableWebSecurity
//授权全局配置
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private NotAuthenticationConfig notAuthenticationConfig;


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //支持跨域
        http.cors().and()
                //csrf关闭
                .csrf().disable()
                //配置哪些需要认证 哪些不需要认证
                .authorizeRequests(rep -> rep.antMatchers(notAuthenticationConfig.getPermitAllUrls().toArray(new String[0]))
                        .permitAll().anyRequest().authenticated())
                .exceptionHandling()
                //认证异常处理
                .authenticationEntryPoint(new ResourceAuthExceptionEntryPoint())
                //授权异常处理
                .accessDeniedHandler(new CustomizeAccessDeniedHandler())
                //登录认证处理
                .and().formLogin()
                .successHandler(new CustomizeAuthenticationSuccessHandler())
                .failureHandler(new CustomizeAuthenticationFailureHandler())
                //登出
                .and().logout().permitAll().addLogoutHandler(new CustomizeLogoutHandler())
                .logoutSuccessHandler(new CustomizeLogoutSuccessHandler())
                .deleteCookies("JSESSIONID")
                .and().userDetailsService(sysUserService);
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean("ssc")
    public SecuritySecurityCheckService permissionService() {
        return new SecuritySecurityCheckService();
    }

}
