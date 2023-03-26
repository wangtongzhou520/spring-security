package org.springsecurity.demo01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springsecurity.demo01.model.SysMenu;
import org.springsecurity.demo01.repository.SysMenuRepository;

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
