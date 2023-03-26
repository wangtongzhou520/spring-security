package org.springsecurity.demo2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springsecurity.demo2.model.SysMenu;

import java.util.List;

/**
 * 菜单
 *
 * @author wangtongzhou 
 * @since 2023-03-19 09:49
 */
@Repository
public interface SysMenuRepository extends CrudRepository<SysMenu,Integer> {

    @Query("select a from SysMenu a left join SysRoleMenu b on a.id=b.menuId " +
            "left join SysRole c on c.id=b.roleId where c.id in (:roleIds)")
    List<SysMenu> queryByRoleIds(@Param("roleIds") List<Long> roleIds);

}
