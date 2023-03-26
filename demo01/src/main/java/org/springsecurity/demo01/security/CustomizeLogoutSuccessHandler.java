package org.springsecurity.demo01.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springsecurity.demo01.common.CommonResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功逻辑处理
 *
 * @author wangtongzhou 
 * @since 2023-03-13 21:39
 */
public class CustomizeLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        CommonResponse result = CommonResponse.ok();
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
