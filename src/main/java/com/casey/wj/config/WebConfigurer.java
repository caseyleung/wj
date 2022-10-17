package com.casey.wj.config;
/*
 * @author CaseyL
 * @date 2022/9/28 23:19
 * */

//import com.casey.wj.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
//    public LoginInterceptor getLoginInterceptor() {
//        return new LoginInterceptor();
//    }

    // 接口拦截
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(getLoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/index.html")
//                .excludePathPatterns("/api/login")
//                .excludePathPatterns("/api/logout")
//                .excludePathPatterns("/api/books")
//                .excludePathPatterns("/api/categories/**")
//                .excludePathPatterns("/api/search")
//                .excludePathPatterns("/api/file/**")
//                .excludePathPatterns("api/covers");
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/file/**")
                .addResourceLocations("file:" + "c:/users/caseyl/desktop/img/");
    }

}
