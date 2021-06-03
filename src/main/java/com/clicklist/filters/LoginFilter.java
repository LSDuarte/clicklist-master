package com.clicklist.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class LoginFilter implements Filter {
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession ses = req.getSession(false);

		String reqURI = req.getRequestURI();

		if (reqURI.indexOf("/home.xhtml") >= 0
				|| (ses != null && ses.getAttribute("username") != null)
				|| reqURI.indexOf("/public/") >= 0
				|| reqURI.contains("javax.faces.resource")) {

			chain.doFilter(request, response);

		} else {
			res.sendRedirect(req.getContextPath() + "/home.xhtml");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
