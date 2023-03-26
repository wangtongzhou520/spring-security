package org.springsecurity.demo01.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springsecurity.demo01.common.CommonResponse;
import org.springsecurity.demo01.common.ResultCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用来处理匿名用户访问无权限资源时的异常
 *
 * @author wangtongzhou 
 * @since 2023-03-06 21:30
 */
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        CommonResponse result= CommonResponse.error(ResultCode.USER_NOT_LOGIN.getCode(),
                ResultCode.USER_NOT_LOGIN.getMessage());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
