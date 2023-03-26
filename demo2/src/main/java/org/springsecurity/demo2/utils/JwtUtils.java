package org.springsecurity.demo2.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * token工具类
 *
 * @author wangtongzhou 
 * @since 2023-03-22 20:32
 */
public class JwtUtils {


    /**
     * 访问令牌有效期
     */
    private static final Long ACCESS_TOKEN_EXPIRE_TIME = 60 * 60 * 1000L;


    /**
     * 访问令牌的秘钥
     */
    private static final String ACCESS_TOKEN_KEY =
            "hello202312345997wwwwweeskkscsdjcalslkasaswyegdhcskdcsdhcksdjcksdcskdsdhcksdkchs";


    /**
     * 刷新令牌有效期
     */
    private static final Long REFRESH_TOKEN_EXPIRE_TIME = 24 * 60 * 60 * 1000L;


    /**
     * 刷新令牌的秘钥
     */
    private static final String REFRESH_TOKEN_KEY =
            "nihao2023dadadajjufncncjskeiinvkaodiasdisaoidaididhscchsducuiwqijakdaksjdaskjdakhdaksjdjas";


    public static String getUUID() {
        String token = UUID.randomUUID().toString().replace("-", "");
        return token;
    }

    public static String createJWTToken(UserDetails userDetails, long timeToExpire) {
        return createJWTToken(userDetails, timeToExpire,
                new SecretKeySpec(ACCESS_TOKEN_KEY.getBytes(StandardCharsets.UTF_8), "HmacSHA512"));
    }

    public static String createAccessToken(UserDetails userDetails) {
        return createJWTToken(userDetails, ACCESS_TOKEN_EXPIRE_TIME);
    }

    public static String createRefreshToken(UserDetails userDetails) {
        return createJWTToken(userDetails, REFRESH_TOKEN_EXPIRE_TIME,
                new SecretKeySpec(REFRESH_TOKEN_KEY.getBytes(StandardCharsets.UTF_8), "HmacSHA512"));
    }


    /**
     * 根据用户信息生成一个 JWT
     *
     * @param userDetails  用户信息
     * @param timeToExpire 毫秒单位的失效时间
     * @param signKey      签名使用的 key
     * @return JWT
     */
    private static String createJWTToken(UserDetails userDetails, long timeToExpire,
                                        Key signKey) {
        return Jwts
                .builder()
                //唯一ID
                .setId(getUUID())
                .setSubject(userDetails.getUsername())
                //权限信息
                .claim("authorities",
                        userDetails.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                //授权信息
                .setIssuedAt(new Date(System.currentTimeMillis()))
                //过期时间
                .setExpiration(new Date(System.currentTimeMillis() + timeToExpire))
                //签名
                .signWith(signKey, SignatureAlgorithm.HS512).compact();
    }


    public static boolean validateAccessToken(String jwtToken) {
        return validateToken(jwtToken, new SecretKeySpec(ACCESS_TOKEN_KEY.getBytes(StandardCharsets.UTF_8), "HmacSHA512"));
    }

    public static boolean validateRefreshToken(String jwtToken) {
        return validateToken(jwtToken, new SecretKeySpec(REFRESH_TOKEN_KEY.getBytes(StandardCharsets.UTF_8), "HmacSHA512"));
    }

    public static boolean validateToken(String jwtToken, Key signKey) {
        return parseClaims(jwtToken, signKey).isPresent();
    }


    public static Optional<Claims> parseAccessTokenClaims(String jwtToken) {
        return Optional.ofNullable(Jwts.parserBuilder().setSigningKey(new SecretKeySpec(ACCESS_TOKEN_KEY.getBytes(StandardCharsets.UTF_8), "HmacSHA512"))
                .build().parseClaimsJws(jwtToken).getBody());
    }

    public static Optional<Claims> parseRefreshTokenClaims(String jwtToken) {
        return Optional.ofNullable(Jwts.parserBuilder().setSigningKey(new SecretKeySpec(REFRESH_TOKEN_KEY.getBytes(StandardCharsets.UTF_8), "HmacSHA512"))
                .build().parseClaimsJws(jwtToken).getBody());
    }


    public static Optional<Claims> parseClaims(String jwtToken, Key signKey) {
        return Optional.ofNullable(Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(jwtToken).getBody());
    }


    public static boolean validateWithoutExpiration(String jwtToken) {
        try {
            Jwts.parserBuilder().setSigningKey(new SecretKeySpec(ACCESS_TOKEN_KEY.getBytes(StandardCharsets.UTF_8), "HmacSHA512"))
                    .build().parseClaimsJws(jwtToken);
            return true;
        } catch (ExpiredJwtException | SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            if (e instanceof ExpiredJwtException) {
                return true;
            }
        }
        return false;
    }

}
