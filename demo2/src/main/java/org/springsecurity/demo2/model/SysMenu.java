package org.springsecurity.demo2.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 菜单权
 *
 * @author wangtongzhou 
 * @since 2023-03-19 09:46
 */
@Entity
@Data
@Table(name = "sys_menu")
public class SysMenu implements Serializable {

    /**
     * 唯一键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 菜单类型
     */
    private Integer type;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 父菜单id
     */
    private Long parentId;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 是否可见
     */
    private String visible;

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

    /**
     * 是否删除
     */
    private String deleted;
}
