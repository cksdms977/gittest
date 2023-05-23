package com.filtertest.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyRequestWrapper extends HttpServletRequestWrapper {

	public MyRequestWrapper(HttpServletRequest request) {
		super(request);

	}
	@Override
	public String getParameter(String name) { // 여기서 들어오는 매개변수는 key값 즉 index.html에서 가져오는 키 값
		String oriData = super.getParameter(name);
		return oriData + "-bs-"; 
	
}

}