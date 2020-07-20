package com.since.whellsurf.filter;

import com.alibaba.fastjson.JSON;
import com.since.whellsurf.common.Config;
import com.since.whellsurf.common.SessionKey;
import com.since.whellsurf.ret.Ret;
import com.since.whellsurf.util.WXUtil;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author luoxinyuan
 */
@WebFilter(filterName = "shopFilter",urlPatterns = {"/*"})
public class ShopFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();
        System.out.println("filter url:"+uri);
        //是否需要过滤
        boolean needFilter = isNeedFilter(uri);

        //不需要过滤直接传给下一个过滤器
        if (!needFilter) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else { //需要过滤器
            // session中包含shop对象,则是登录状态
            if(session!=null&&session.getAttribute(SessionKey.LOGIN_SHOP) != null){
                filterChain.doFilter(request, response);
            }else{
                String requestType = request.getHeader("X-Requested-With");
                //判断是否是ajax请求
                String redirectURL = WXUtil.genGetUserURL(Config.appId,Config.SHOP_NO_PERMISSION_REDIRECT);
                if(requestType!=null && "XMLHttpRequest".equals(requestType)){
                    response.getWriter().write(JSON.toJSONString(Ret.noPermission(redirectURL)));
                }else{
                    //重定向到登录页(需要在static文件夹下建立此html文件)
                    response.sendRedirect(request.getContextPath()+redirectURL);
                }
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
        for (String includeUrl : Config.shopURL) {
            if(includeUrl.equals(uri)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }
}