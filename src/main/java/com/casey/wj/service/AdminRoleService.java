package com.casey.wj.service;
/*
 * @author CaseyL
 * @date 2022/10/3 15:56
 * */

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.casey.wj.dao.*;
import com.casey.wj.dto.CommonResponseDto;
import com.casey.wj.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminRoleService {
    private final UserDao userDao;
    private final AdminRoleDao roleDao;
    private final AdminUserRoleDao userRoleDao;
    private final AdminRolePermissionDao rolePermissionDao;
    private final AdminPermissionService permissionService;
    private final AdminRoleMenuDao roleMenuDao;
    private final AdminRoleMenuService roleMenuService;


    public Set<String> getUserRoleByUserame(String username) {
        User user = userDao.getUserByUsername(username);
        // 找到用户所有rids
        List<Integer> rids = userRoleDao.findAllByUid(user.getId()).stream()
                .map(AdminUserRole::getRid).collect(Collectors.toList());

        Set<String> roles = roleDao.findAllById(rids).stream()
                .map(AdminRole::getName).collect(Collectors.toSet());

        return roles;
    }

    public CommonResponseDto getAllRoles() {
        CommonResponseDto responseDto = new CommonResponseDto();

        List<AdminRole> all = roleDao.findAll();
        JSONArray roleList = new JSONArray();
        for (AdminRole role: all) {
            JSONObject object = new JSONObject();
            object.putOpt("id", role.getId());
            object.putOpt("name", role.getName());
            object.putOpt("nameZh", role.getNameZh());
            boolean enabled = false;
            String userStatus = role.getEnabled();
            if (userStatus != null) {
                enabled = userStatus.equals("Y");
            }
            object.putOpt("enabled", enabled);
            // rid ---> menu  { admin -->  [1,2,3,5,6,7] [包括子节点] (单个角色对应的id) }
            List<AdminMenu> menus = roleMenuService.findAllByRid(role.getId());
            object.putOpt("menus", menus);
            // rid ---> perms  { admin -->  [1,2,3]  }
            List<AdminPermission> perms = permissionService.findPermsByRid(role.getId());
            object.putOpt("perms", perms);
            roleList.add(object);
        }
        responseDto.setObject(roleList);
        return responseDto;
    }

    public CommonResponseDto changeRoleStatus(AdminRole currentRole) {
        CommonResponseDto responseDto = new CommonResponseDto();
        AdminRole role = roleDao.findById(currentRole.getId()).orElse(null);
        // optional 的写法
        if (role == null) {
            responseDto.setCode(400);
            return responseDto;
        }

        String flag = "true".equals(currentRole.getEnabled())? "Y" : "N";
        role.setEnabled(flag);
        responseDto.setCode(200);
        roleDao.save(role);
        return responseDto;
    }

    @Transactional
    public CommonResponseDto addUpdateUser(AdminRole role) {
        CommonResponseDto responseDto = new CommonResponseDto();
        // 分别录入角色，菜单和权限
        // 先找到role先
        AdminRole currentRole = roleDao.findById(role.getId()).orElse(null);
        if (currentRole != null) {
            currentRole.setName(role.getName());
            currentRole.setNameZh(role.getNameZh());
            roleDao.save(currentRole);

            // 删除原先role所对应的permission，admin_role_permissions里面的包含rid都删掉
            rolePermissionDao.deleteByRid(role.getId());
            // 然后添加现在的pids进去。
            // pid
            List<String> pids = role.getPerms();
            for (String p : pids) {
                int pid = Integer.parseInt(p);
                AdminRolePermission rolePermission = new AdminRolePermission();
                rolePermission.setRid(role.getId());
                rolePermission.setPid(pid);
                rolePermissionDao.save(rolePermission);
            }

            // 对于mid同理
            roleMenuDao.deleteByRid(role.getId());
            // mid
            List<String> mids = role.getMenus();
            for (String m : mids) {
                int mid = Integer.parseInt(m);
                AdminRoleMenu roleMenu = new AdminRoleMenu();
                roleMenu.setRid(role.getId());
                roleMenu.setMid(mid);
                roleMenuDao.save(roleMenu);
            }
            responseDto.setMessage("修改角色信息成功");

        } else {
            AdminRole newRole = new AdminRole();
            newRole.setName(role.getName());
            newRole.setNameZh(role.getNameZh());
            roleDao.save(newRole);
            responseDto.setMessage("新增角色成功");
        }

        responseDto.setCode(200);
        return responseDto;
    }

    @Transactional
    public CommonResponseDto deleteRole(Integer rid) {
        CommonResponseDto responseDto = new CommonResponseDto();
        // 删除对应的角色
        roleDao.deleteById(rid);
        // 涉及到role_permission, role_menu, user_role的rid
        roleMenuDao.deleteByRid(rid);
        rolePermissionDao.deleteByRid(rid);
        userRoleDao.deleteByRid(rid);
        responseDto.setCode(200);
        responseDto.setMessage("角色删除成功");
        return responseDto;
    }
}
