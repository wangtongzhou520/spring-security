package org.springsecurity.demo2.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录
 *
 * @author wangtongzhou 
 * @since 2023-03-20 21:30
 */
@Data
public class LoginDTO implements Serializable {


    private String username;

    private String password;

}
