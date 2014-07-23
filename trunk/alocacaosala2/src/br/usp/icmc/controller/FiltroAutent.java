package br.usp.icmc.controller;

import java.io.IOException;

import javax.servlet.DispatcherType;
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

/**
 * Servlet Filter implementation class FiltroAutent
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD }, urlPatterns = { "/*" })
public class FiltroAutent implements Filter {
	/**
	 * Default constructor.
	 */
	public FiltroAutent() {

	}
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession sessao = httpServletRequest.getSession();
		String url = httpServletRequest.getRequestURI();
		if (sessao.getAttribute("logado") != null
				|| url.lastIndexOf("login.jsp") > -1
				|| url.lastIndexOf("autent.do") > -1
				|| url.lastIndexOf("visualizar.jsp") > -1) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect("login.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
}
