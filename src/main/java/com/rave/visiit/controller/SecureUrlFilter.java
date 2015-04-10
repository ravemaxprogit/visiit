package com.rave.visiit.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.rave.visiit.util.Constants;

//public class SecureUrlFilter extends GenericFilterBean{
public class SecureUrlFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {

		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        
        HttpServletRequest request = (HttpServletRequest) req;
		Cookie[] cookies = request.getCookies();

		boolean isValidCookiePresent = false;
		if(null!=cookies){
			
			for(Cookie cookie : cookies){
				if(null!=cookie && Constants.USER_DETAILS_STR.equals(cookie.getName()) && StringUtils.isNotBlank(cookie.getValue())){
					isValidCookiePresent = true;
					break;
				}
			}
		}

		if(isValidCookiePresent==false){
			response.sendRedirect(request.getContextPath()+"/#/login");
		}
		filterChain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
