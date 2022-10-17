package com.casey.wj.controller;
/*
 * @author CaseyL
 * @date 2022/10/13 8:36
 * */

import com.casey.wj.dto.CommonResponseDto;
import com.casey.wj.entity.AdminRole;
import com.casey.wj.service.AdminRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RoleController {

    private final AdminRoleService roleService;

    //admin/role/changeStatus
    @PutMapping("api/admin/role/changeStatus")
    public CommonResponseDto changeRoleStatus(@RequestBody AdminRole role) {
        return roleService.changeRoleStatus(role);
    }

    @GetMapping("/api/admin/roles")
    public CommonResponseDto getAllRoles() {
        return roleService.getAllRoles();
    }

    @PutMapping("/api/admin/roles")
    public CommonResponseDto addUpdateUser(@RequestBody AdminRole role) {
        return roleService.addUpdateUser(role);
    }

    @DeleteMapping("api/admin/roles/delete/{id}")
    public CommonResponseDto deleteRole(@PathVariable(value = "id") Integer id) {
        return roleService.deleteRole(id);
    }

}
