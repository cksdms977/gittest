package com.web.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class PasswordEncriptFilter
 */
@WebFilter(urlPatterns = {"/member/*"}, servletNames = {"login"})
public class PasswordEncryptFilter extends HttpFilter implements Filter {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 8867609428306720L;

	/**
     * @see HttpFilter#HttpFilter()
     */
    public PasswordEncryptFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		암호화처리하는 로직을 생성...
//		password호출했을때 함호화처리
//		getParameter()메소드를 재정의해서 사용
		
		PasswordEncryptoWrapper pwew = new PasswordEncryptoWrapper((HttpServletRequest)request);
		
		chain.doFilter(pwew, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
