package com.casey.wj.service;

import com.casey.wj.dto.CommonResponseDto;
import com.casey.wj.entity.User;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/*
 * @author CaseyL
 * @date 2022/10/16 13:33
 * */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    public CommonResponseDto login(String username, String password, boolean rememberMe) {
        CommonResponseDto responseDto = new CommonResponseDto();

        User currentUser = userService.getUserByName(username);
        Subject subject = SecurityUtils.getSubject();
        System.out.println("---  rememberMe: " + rememberMe);

        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        subject.getSession().setAttribute("user", currentUser);

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            responseDto.setCode(400);
            responseDto.setMessage("账号或密码错误");
            return responseDto;
        }

        responseDto.setCode(200);
        responseDto.setMessage("登陆成功");
        responseDto.setObject(username);
        return responseDto;
    }

}
