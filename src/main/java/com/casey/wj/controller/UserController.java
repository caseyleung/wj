package com.casey.wj.controller;
/*
 * @author CaseyL
 * @date 2022/10/4 20:21
 * */

import com.casey.wj.dto.CommonResponseDto;
import com.casey.wj.entity.User;
import com.casey.wj.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @RequiresPermissions("/api/admin/user")
    @GetMapping("/api/admin/user")
    public CommonResponseDto getAllUsers() {
        return userService.getAllUsers();
    }


    @PutMapping("api/admin/user/changeUserStatus")
    public CommonResponseDto changeUserStatus(@RequestBody User user) {
        return userService.chagneUserStatus(user.getUsername(), user.getEnabled());
    }


    @PostMapping("api/admin/user")
    @ResponseBody
    public CommonResponseDto updateAddUser(@RequestBody User user) {
        return userService.updateAddUser(user);
    }

    @DeleteMapping("api/admin/user/delete/{id}")
//    @ResponseBody
    public CommonResponseDto deleteUser(@PathVariable(value = "id") Integer id) {
        return userService.deleteUser(id);
    }

    @PutMapping("api/admin/user/resetPwd")
    public CommonResponseDto resetPwd(@RequestBody User user) {
        String username = user.getUsername();
        return userService.resetPassword(username);
    }
}
