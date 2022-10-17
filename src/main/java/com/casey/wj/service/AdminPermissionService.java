package com.casey.wj.service;
/*
 * @author CaseyL
 * @date 2022/10/4 9:36
 * */

import com.casey.wj.dao.AdminPermissionDao;
import com.casey.wj.dao.AdminRolePermissionDao;
import com.casey.wj.dao.AdminUserRoleDao;
import com.casey.wj.dao.UserDao;
import com.casey.wj.entity.AdminPermission;
import com.casey.wj.entity.AdminRolePermission;
import com.casey.wj.entity.AdminUserRole;
import com.casey.wj.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminPermissionService {

    private final UserDao userDao;
    private final AdminUserRoleDao userRoleDao;
    private final AdminPermissionDao permissionDao;
    private final AdminRolePermissionDao rolePermissionDao;


    // 通过username找到admin_role
    public Set<String> findPermissionByUser(String username) {
        // username  --> admin_user_role  ---> admin_role  --->  admin_permission(urls)

        User user = userDao.getUserByUsername(username);
        List<Integer> rids = userRoleDao.findAllByUid(user.getId()).stream()
                .map(AdminUserRole::getRid).collect(Collectors.toList());

        List<Integer> pids = rolePermissionDao.findAllByRidIn(rids).stream()
                .map(AdminRolePermission::getPid).collect(Collectors.toList());

        Set<String> urls = permissionDao.findAllById(pids).stream()
                .map(AdminPermission::getUrl).collect(Collectors.toSet());

//        List<AdminPermission> perms = permissionDao.findAllById(pids);
//        Set<String> URLs = perms.stream().map(AdminPermission::getUrl).collect(Collectors.toSet());

        return urls;
    }

    public boolean URLsFilter(String requestAPI) {
        List<AdminPermission> all = permissionDao.findAll();
        for (AdminPermission permission : all) {
            if (requestAPI.startsWith(permission.getUrl())) {
                return true;
            }
        }
        return false;
    }

    // 单个角色对应的权限
    public List<AdminPermission> findPermsByRid(int rid) {
        // rid(list) --> pid --> perms
        List<Integer> pids = rolePermissionDao.findAllByRid(rid).stream()
                .map(AdminRolePermission::getPid).collect(Collectors.toList());

        return new ArrayList<>(permissionDao.findAllById(pids));
    }

    public List<AdminPermission> getAllPermissions() {
        return permissionDao.findAll();
    }
}
