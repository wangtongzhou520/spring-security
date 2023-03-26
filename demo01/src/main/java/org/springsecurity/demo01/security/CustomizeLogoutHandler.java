package org.springsecurity.demo01.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义登出逻辑
 *
 * @author wangtongzhou 
 * @since 2023-03-13 21:53
 */
public class CustomizeLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

    }
}
