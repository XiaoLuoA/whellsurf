package com.since.whellsurf.filter;

import com.alibaba.fastjson.JSON;
import com.since.whellsurf.common.Config;
import com.since.whellsurf.common.SessionKey;
import com.since.whellsurf.ret.Result;
import com.since.whellsurf.ret.Ret;
import com.since.whellsurf.util.WXUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class SessionFilter implements Filter {
    //不需要登录就可以访问的路径(比如:注册登录等)
    String[] excludeUrls = new String[]{};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();
        System.out.println("filter url:"+uri);
        //是否需要过滤
        boolean needFilter = isNeedFilter(uri);


        if (!needFilter) { //不需要过滤直接传给下一个过滤器
            filterChain.doFilter(servletRequest, servletResponse);
        } else { //需要过滤器
            // session中包含user对象,则是登录状态
          if(session!=null&&session.getAttribute(SessionKey.LOGIN_USER) != null){
                filterChain.doFilter(request, response);
            }else{
                String requestType = request.getHeader("X-Requested-With");
                //判断是否是ajax请求
                if(requestType!=null && "XMLHttpRequest".equals(requestType)){
                    response.getWriter().write(JSON.toJSONString(Ret.error(Result.NO_PERMISSION)));
                }else{
                    //重定向到登录页(需要在static文件夹下建立此html文件)
                    String redirectURL = WXUtil.genGetUserURL(Config.appId,Config.HOST);
                    response.sendRedirect(request.getContextPath()+redirectURL);
                }
                return;
            }
        }
    }

    /**
     * @Author: xxxxx
     * @Description: 是否需要过滤
     * @Date: 2018-03-12 13:20:54
     * @param uri
     */
    public boolean isNeedFilter(String uri) {

        for (String includeUrl : excludeUrls) {
            if(includeUrl.equals(uri)) {
                return false;
            }
        }
        if (uri.contains(Config.ACTIVITY_INDEX)||uri.contains("/pay/notify/order")){
            return false;
        }
        return true;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
