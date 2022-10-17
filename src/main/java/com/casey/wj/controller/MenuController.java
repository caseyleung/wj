package com.casey.wj.controller;
/*
 * @author CaseyL
 * @date 2022/10/3 15:58
 * */

import com.casey.wj.dto.CommonResponseDto;
import com.casey.wj.entity.AdminMenu;
import com.casey.wj.entity.AdminPermission;
import com.casey.wj.entity.User;
import com.casey.wj.service.AdminMenuService;
import com.casey.wj.service.AdminPermissionService;
import com.casey.wj.service.AdminRoleService;
import com.casey.wj.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController {
    private final AdminMenuService menuService;
    private final UserService userService;
    private final AdminRoleService roleService;
    private final AdminPermissionService permissionService;

    @GetMapping("api/menu")
    public CommonResponseDto menu() {
        Subject subject = SecurityUtils.getSubject();
        User user = userService.getUserByName(subject.getPrincipal().toString());
        return menuService.getMenuByCurrentUser(user);
    }

//    @GetMapping("api/perm")
//    public CommonResponseDto permission() {
//        Subject subject = SecurityUtils.getSubject();
//        User user = userService.getUserByName(subject.getPrincipal().toString());
//        return permissionService.findPermissionByUser(user.getUsername());
//    }
    @GetMapping("api/admin/roles/menus")
    public CommonResponseDto getAllMenus() {
        CommonResponseDto responseDto = new CommonResponseDto();
        List<AdminMenu> allMenus = menuService.getAllMenus();
        responseDto.setObject(allMenus);
        return responseDto;
    }
    @GetMapping("api/admin/roles/perms")
    public CommonResponseDto getAllPermissions() {
        CommonResponseDto responseDto = new CommonResponseDto();
        List<AdminPermission> permList = permissionService.getAllPermissions();
        responseDto.setObject(permList);
        return responseDto;
    }


}
