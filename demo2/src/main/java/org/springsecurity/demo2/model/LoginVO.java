package org.springsecurity.demo2.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录以后返回的VO
 *
 * @author wangtongzhou 
 * @since 2023-03-20 21:26
 */
@Data
public class LoginVO implements Serializable {

    private String accessToken;

    private String refreshToken;

    private String userName;


}
