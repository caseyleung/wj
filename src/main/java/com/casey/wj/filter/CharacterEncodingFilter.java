package com.casey.wj.filter;
/*
 * @author CaseyL
 * @date 2022/9/30 14:37
 * */


//@WebFilter(urlPatterns = "/*", filterName = "CharacterEncodingFilter")
//public class CharacterEncodingFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//
//        filterChain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
