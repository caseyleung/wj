package com.casey.wj.exception;
/*
 * @author CaseyL
 * @date 2022/10/5 22:14
 * */

import com.casey.wj.dto.CommonResponseDto;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResponseDto handleAuthorizationException(Exception e) {
        CommonResponseDto responseDto = new CommonResponseDto();
        String message = null;
        message = e.getMessage();
        if (e instanceof IllegalArgumentException) {
            message = "传入了错误的参数";
        }

        if (e instanceof MethodArgumentNotValidException) {
            message = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage();
        }

        if (e instanceof UnauthorizedException) {
            message = "权限认证失败";
        }

        responseDto.setMessage(message);
        System.out.println("---------UnauthorizedException-----------" + "/n :" + message);
        return responseDto;
    }
}
