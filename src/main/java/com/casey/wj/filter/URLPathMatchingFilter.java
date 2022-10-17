package com.casey.wj.filter;
/*
 * @author CaseyL
 * @date 2022/10/4 13:48
 * */

import com.casey.wj.service.AdminPermissionService;
import com.casey.wj.utils.SpringContextutils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

//@RequiredArgsConstructor
public class URLPathMatchingFilter extends PathMatchingFilter {

    @Autowired
    AdminPermissionService permissionService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // 放行options 请求
        if (HttpMethod.OPTIONS.toString().equals(httpServletRequest.getMethod())) {
            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }

        String requestAPI = getPathWithinApplication(request);
        System.out.println("请求路径: "+ requestAPI);

        Subject subject = SecurityUtils.getSubject();

        if (!subject.isAuthenticated()) {
            System.out.println("当前用户未登录，请先登录");
            return false;
        }

        if (null==permissionService) {
            permissionService = SpringContextutils.getContext().getBean(AdminPermissionService.class);
        }


        boolean urLsFilter = permissionService.URLsFilter(requestAPI);
        if (!urLsFilter) {
            System.out.println("api: " + requestAPI + " 需要认证");
            return true;
        } else {
            System.out.println("api: " + requestAPI + " 已认证");
            boolean hasPermission = false;
            String username = subject.getPrincipal().toString();
            Set<String> permissionAPIs = permissionService.findPermissionByUser(username);
            for (String api : permissionAPIs) {
                if (requestAPI.startsWith(api)){
                    hasPermission = true;
                    break;
                }
            }
            if (hasPermission) {
                return true;
            } else {
                System.out.println("api: " + requestAPI + ", user is not authorized");
                return false;
            }
        }
    }
}
