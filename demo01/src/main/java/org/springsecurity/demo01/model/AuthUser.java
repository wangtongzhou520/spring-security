package org.springsecurity.demo01.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 实现UserDetails
 *
 * @author wangtongzhou 
 * @since 2023-03-06 20:01
 */
public class AuthUser extends User {

    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
