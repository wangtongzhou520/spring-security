package org.springsecurity.demo2.service;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springsecurity.demo2.model.AuthUser;
import org.springsecurity.demo2.model.LoginDTO;
import org.springsecurity.demo2.model.LoginVO;
import org.springsecurity.demo2.model.SysUser;
import org.springsecurity.demo2.repository.SysUserRepository;
import org.springsecurity.demo2.utils.JwtUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * 登录接口
 *
 * @author wangtongzhou 
 * @since 2023-03-20 21:37
 */
@Service
public class LoginService {


    @Autowired
    private AuthenticationManager authenticationManager ;

    @Autowired
    private SysUserService sysUserService;


    public LoginVO login(LoginDTO loginDTO) {
        //创建Authentication对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
                loginDTO.getPassword());

        //调用AuthenticationManager的authenticate方法进行认证
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if(authentication == null) {
            throw new RuntimeException("用户名或密码错误");
        }
        //登录成功以后用户信息、
        AuthUser authUser =(AuthUser)authentication.getPrincipal();

        LoginVO loginVO=new LoginVO();
        loginVO.setUserName(authUser.getUsername());
        loginVO.setAccessToken(JwtUtils.createAccessToken(authUser));
        loginVO.setRefreshToken(JwtUtils.createRefreshToken(authUser));

        return loginVO;
    }


    public LoginVO refreshToken(String accessToken, String refreshToken){
        if (!JwtUtils.validateRefreshToken(refreshToken) && !JwtUtils.validateWithoutExpiration(accessToken)) {
            throw new RuntimeException("认证失败");
        }
        Optional<String> userName = JwtUtils.parseRefreshTokenClaims(refreshToken).map(Claims::getSubject);
        if (userName.isPresent()){
            AuthUser authUser = sysUserService.loadUserByUsername(userName.get());
            if (Objects.nonNull(authUser)) {
                LoginVO loginVO=new LoginVO();
                loginVO.setUserName(authUser.getUsername());
                loginVO.setAccessToken(JwtUtils.createAccessToken(authUser));
                loginVO.setRefreshToken(JwtUtils.createRefreshToken(authUser));
                return loginVO;
            }
            throw new InternalAuthenticationServiceException("用户不存在");
        }
        throw new RuntimeException("认证失败");
    }
}
