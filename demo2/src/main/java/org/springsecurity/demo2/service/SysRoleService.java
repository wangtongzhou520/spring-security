package org.springsecurity.demo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springsecurity.demo2.model.SysRole;
import org.springsecurity.demo2.repository.SysRoleRepository;

import java.util.List;

/**
 * 角色查询
 *
 * @author wangtongzhou 
 * @since 2023-03-14 22:12
 */
@Service
public class SysRoleService {

    @Autowired
    private SysRoleRepository roleRepository;


    public List<SysRole> queryByUserName(String userName){
        List<SysRole> roles=roleRepository.queryByUserName(userName);
        return roles;
    }

}
