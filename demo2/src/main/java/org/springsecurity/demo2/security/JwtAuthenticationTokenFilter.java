package org.springsecurity.demo2.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springsecurity.demo2.utils.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * jwt token 请求过滤
 *
 * @author wangtongzhou
 * @since 2023-03-25 10:58
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        //check Token
        if (checkJWTToken(request)) {
            //解析token中的认证信息
            Optional<Claims> claimsOptional = validateToken(request)
                    .filter(claims -> claims.get("authorities") != null);
            if (claimsOptional.isPresent()) {
                List<String> authoritiesList = castList(claimsOptional.get().get("authorities"), String.class);
                List<SimpleGrantedAuthority> authorities = authoritiesList
                        .stream().map(String::valueOf)
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(claimsOptional.get().getSubject(), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                SecurityContextHolder.clearContext();
            }
        }
        chain.doFilter(request, response);
    }

    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

    private Optional<Claims> validateToken(HttpServletRequest req) {
        String jwtToken = req.getHeader("token");
        try {
            return JwtUtils.parseAccessTokenClaims(jwtToken);
        } catch (ExpiredJwtException | SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            //输出日志
            return Optional.empty();
        }
    }

    private boolean checkJWTToken(HttpServletRequest request) {
        String authenticationHeader = request.getHeader("token");
        return authenticationHeader != null;
    }
}
