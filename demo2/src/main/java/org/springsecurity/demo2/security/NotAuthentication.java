package org.springsecurity.demo2.security;

import java.lang.annotation.*;

/**
 * 该注解标志所有都可以不需要认证可以访问
 *
 * @author wangtongzhou 
 * @since 2023-03-19 16:05
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface NotAuthentication {
}
