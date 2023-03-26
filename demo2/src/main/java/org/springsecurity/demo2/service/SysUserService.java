package org.springsecurity.demo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springsecurity.demo2.model.AuthUser;
import org.springsecurity.demo2.model.SysMenu;
import org.springsecurity.demo2.model.SysRole;
import org.springsecurity.demo2.model.SysUser;
import org.springsecurity.demo2.repository.SysUserRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户
 *
 * @author wangtongzhou 
 * @since 2023-03-04 22:25
 */
@Service
public class SysUserService implements UserDetailsService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;


    @Override
    public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SysUser> sysUser = Optional.ofNullable(sysUserRepository.findOptionalByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("未找到用户名")));
        List<SysRole> roles = sysRoleService.queryByUserName(sysUser.get().getUsername());
        Set<String> dbAuthsSet = new HashSet<>();
        if (!CollectionUtils.isEmpty(roles)) {
            //角色
            roles.forEach(x -> {
                dbAuthsSet.add("ROLE_" + x.getName());
            });
            List<Long> roleIds = roles.stream().map(SysRole::getId).collect(Collectors.toList());
            List<SysMenu> menus = sysMenuService.queryByRoleIds(roleIds);
            //菜单
            Set<String> permissions= menus.stream().filter(x->x.getType().equals(3))
                    .map(SysMenu::getPermission).collect(Collectors.toSet());
            dbAuthsSet.addAll(permissions);
        }
        Collection<GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList(dbAuthsSet.toArray(new String[0]));
        return new AuthUser(username, sysUser.get().getPassword(), authorities);
    }



}
