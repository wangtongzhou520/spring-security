package org.springsecurity.demo01.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户vo
 *
 * @author wangtongzhou 
 * @since 2023-03-25 21:36
 */
@Data
public class UserVO implements Serializable {

    private String userName;
}
