package com.casey.wj.controller;
/*
 * @author CaseyL
 * @date 2022/9/28 17:20
 * */

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.casey.wj.dto.CommonResponseDto;
import com.casey.wj.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequiredArgsConstructor
public class LoginController {
    private final AuthService authService;

    @ResponseBody
    @PostMapping("api/login")
    public CommonResponseDto login(@RequestBody String json) {
        JSONObject object = JSONUtil.parseObj(json);
        String username = object.getStr("username");
        String password = object.getStr("password");
        Boolean rememberMe = object.getBool("rememberMe");
        return authService.login(username, password, rememberMe);
    }


    @ResponseBody
    @GetMapping("api/logout")
    public CommonResponseDto logout() {
        CommonResponseDto responseDto = new CommonResponseDto();
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        responseDto.setMessage("成功登出");
        responseDto.setCode(200);
        return responseDto;
    }

}
