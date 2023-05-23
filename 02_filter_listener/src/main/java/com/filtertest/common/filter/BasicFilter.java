package com.filtertest.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class BasicFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
		
		System.out.println("BasicFilter실행함!!");
		
		//wrapper클래스적용하기
		MyRequestWrapper mrw = new MyRequestWrapper((HttpServletRequest)request);
		
//		다음 로직이 실행될 수 있게 하기
//		chain.doFilter(request, response);
		chain.doFilter(mrw, response); // 이렇게 mrw를 넘기게 된다.
	
	}
	
	
	
	
}
