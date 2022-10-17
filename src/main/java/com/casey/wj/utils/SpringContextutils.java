package com.casey.wj.utils;
/*
 * @author CaseyL
 * @date 2022/10/4 14:35
 * */

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Data
@Component
public class SpringContextutils implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextutils.context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }


}
