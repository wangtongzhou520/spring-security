package org.springsecurity.demo01.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户角色关系
 *
 * @author wangtongzhou 
 * @since 2023-03-14 20:57
 */
@Entity
@Data
@Table(name = "sys_user_role")
public class SysUserRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 创建者
     */
    private String createOperator;

    /**
     * 修改者
     */
    private String modifiedOperator;

    /**
     * 修改者ip
     */
    private String modifiedOperatorIp;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 是否删除
     */
    private String deleted;
}
