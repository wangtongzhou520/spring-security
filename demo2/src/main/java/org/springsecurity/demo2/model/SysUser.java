package org.springsecurity.demo2.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * @author wangtongzhou 
 * @since 2023-03-04 22:16
 */
@Entity
@Data
@Table(name="sys_user")
public class SysUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户所在部门的id
     */
    private Integer deptId;

    /**
     * 状态，1：正常，0：冻结状态，2：删除
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建者
     */
    private String createOperator;

    /**
     * 修改者
     */
    private String modifiedOperator;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;


}
