package org.springsecurity.demo2.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色信息
 *
 * @author wangtongzhou 
 * @since 2023-03-14 20:57
 */
@Entity
@Data
@Table(name="sys_role")
public class SysRole implements Serializable {

    /**
     * 角色id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色的类型，1：管理员角色，2：其他
     */
    private int type;

    /**
     * 状态，1：可用，0：冻结
     */
    private int status;

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

    /**
     * 角色权限字符串
     */
    private String code;

}
