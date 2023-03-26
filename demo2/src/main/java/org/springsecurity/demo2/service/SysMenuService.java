package org.springsecurity.demo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springsecurity.demo2.model.SysMenu;
import org.springsecurity.demo2.repository.SysMenuRepository;

import java.util.List;

/**
 * 菜单
 *
 * @author wangtongzhou 
 * @since 2023-03-19 09:48
 */
@Service
public class SysMenuService {

    @Autowired
    private SysMenuRepository sysMenuRepository;


    public List<SysMenu> queryByRoleIds(List<Long> roleIds) {
        return sysMenuRepository.queryByRoleIds(roleIds);
    }
}
