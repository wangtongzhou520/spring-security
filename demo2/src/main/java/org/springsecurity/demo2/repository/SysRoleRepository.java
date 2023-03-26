package org.springsecurity.demo2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springsecurity.demo2.model.SysRole;
import org.springsecurity.demo2.model.SysUser;

import java.util.List;

/**
 * 角色
 *
 * @author wangtongzhou 
 * @since 2023-03-14 22:06
 */
@Repository
public interface SysRoleRepository extends CrudRepository<SysUser,Integer> {

    @Query("select a from SysRole a left join SysUserRole b on a.id=b.roleId " +
            "left join SysUser c on c.id=b.userId where c.username=:userName")
    List<SysRole> queryByUserName(@Param("userName") String userName);
}
