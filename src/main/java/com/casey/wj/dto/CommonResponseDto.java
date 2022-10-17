package com.casey.wj.dto;
/*
 * @author CaseyL
 * @date 2022/9/28 17:25
 * */

import lombok.Data;

@Data
public class CommonResponseDto {
    private int code;
    private String message;
    private Object object;
}
