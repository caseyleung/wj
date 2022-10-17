package com.casey.wj.shiro;
/*
 * @author CaseyL
 * null 2022/10/1 22:50
 * */

import com.casey.wj.entity.User;
import com.casey.wj.service.AdminPermissionService;
import com.casey.wj.service.AdminRoleService;
import com.casey.wj.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Set;

@RequiredArgsConstructor
public class WJRealm extends AuthorizingRealm {
    private final UserService userService;
    private final AdminPermissionService permissionService;
    private final AdminRoleService roleService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = principalCollection.getPrimaryPrincipal().toString();
        Set<String> permissions = permissionService.findPermissionByUser(username);
        Set<String> roles = roleService.getUserRoleByUserame(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(permissions);
        authorizationInfo.setRoles(roles);
        System.out.println("-------------authorizationInfo----------------");
        System.out.println("permissions: " + permissions);
        System.out.println("roles: " + roles);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = authenticationToken.getPrincipal().toString();
        User user = userService.getUserByName(username);
        String password = user.getPassword();
        String salt = user.getSalt();
        SimpleAuthenticationInfo authenticationInfo
                = new SimpleAuthenticationInfo(username, password, ByteSource.Util.bytes(salt),getName());
        System.out.println("-----------doGetAuthenticationInfo----------");
        return authenticationInfo;

    }
}
