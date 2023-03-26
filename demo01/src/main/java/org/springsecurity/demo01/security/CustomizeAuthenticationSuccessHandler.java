package org.springsecurity.demo01.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springsecurity.demo01.common.CommonResponse;
import org.springsecurity.demo01.model.AuthUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功以后处理
 *
 * @author wangtongzhou 
 * @since 2023-03-12 20:49
 */
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //返回json数据
        CommonResponse<AuthUser> result = CommonResponse.ok(authUser);
        //处理编码方式，防止中文乱码的情况
        response.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        response.getWriter().write(JSON.toJSONString(result));
    }
}
