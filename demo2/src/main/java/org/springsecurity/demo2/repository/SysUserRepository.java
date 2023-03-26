package org.springsecurity.demo2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springsecurity.demo2.model.SysUser;

import java.util.Optional;

/**
 * 用户
 *
 * @author wangtongzhou 
 * @since 2023-03-04 22:23
 */
@Repository
public interface SysUserRepository extends CrudRepository<SysUser,Integer> {

    Optional<SysUser> findOptionalByUsername(String username);
}
