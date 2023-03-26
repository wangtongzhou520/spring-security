package org.springsecurity.demo01.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 权限菜单关系
 *
 * @author wangtongzhou 
 * @since 2023-03-19 09:44
 */
@Entity
@Data
@Table(name = "sys_role_menu")
public class SysRoleMenu implements Serializable {
    /**
     * 自增编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 菜单id
     */
    private Long menuId;

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
