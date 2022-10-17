package com.casey.wj.controller;
/*
 * @author CaseyL
 * @date 2022/10/1 20:23
 * */

import com.casey.wj.dto.CommonResponseDto;
import com.casey.wj.entity.User;
import com.casey.wj.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin
public class RegisterationController {

    private UserService userService;

    @PostMapping("api/register")
    @ResponseBody
    public CommonResponseDto register(@RequestBody User user) {
        return userService.registerUser(user);
    }
}
