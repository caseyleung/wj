package com.casey.wj.error;
/*
 * @author CaseyL
 * @date 2022/9/28 23:25
 * */

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorConfig implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage errorPage = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
        System.out.println("--------------error----------");
        registry.addErrorPages(errorPage);
    }
}
