package com.xiamo.common.utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <dl>
 * <dt>CORSFilter</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/1/22 0022</dd>
 * </dl>
 *
 * @author chenglitao
 */
public class CORSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;


        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");


        httpServletResponse
                .setHeader(
                        "Access-Control-Allow-Headers",
                        "User-Agent,Origin,Cache-Control,Content-type,Date,Server,withCredentials,AccessToken");


        httpServletResponse.setHeader("Access-Control-Allow-Credentials",
                "true");


        httpServletResponse.setHeader("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");


        httpServletResponse.setHeader("Access-Control-Max-Age", "1209600");


        httpServletResponse.setHeader("Access-Control-Expose-Headers",
                "accesstoken");


        httpServletResponse.setHeader("Access-Control-Request-Headers",
                "accesstoken");


        httpServletResponse.setHeader("Expires", "-1");


        httpServletResponse.setHeader("Cache-Control", "no-cache");


        httpServletResponse.setHeader("pragma", "no-cache");


        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
