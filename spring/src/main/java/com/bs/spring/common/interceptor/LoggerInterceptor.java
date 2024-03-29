package com.bs.spring.common.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.demo.controller.DemoController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//반환형이 booean true반환하면 매핑메소드가 실행, false반환하면 매핑메소드 실행을 하지 않음
		log.debug("------- 인터셉터 prehandle 실행 --------");
		log.debug(request.getRequestURI());
		Map params = request.getParameterMap();
		for(Object key : params.keySet()) {
			System.out.println(key);
		}
		log.debug("-----------------------------------");
//		response.sendRedirect(request.getContextPath());
//		hander -> 실행되는 controller클래스, 실행되는 메소드를 확인할수 있다.
		HandlerMethod hm = (HandlerMethod)handler;
		log.debug("{}",hm.getBean()); // 여기서 bean은 생성된 객체들을 확인할수 있음
		DemoController  demo = (DemoController)hm.getBean();
		log.debug("{}", hm.getMethod());
		Method m = hm.getMethod();
		return true; // true하면 응답한것을 출력해줄수 있고, false로 설정하면 요청을 받는것을 차단시킬수 있다.
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.debug("------- 인터셉터 postHandle -------");
		log.debug("{}", modelAndView.getViewName());
		Map modelData = modelAndView.getModel();
		log.debug("{}", modelData);
		log.debug("--------------------------------");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception { // 예외가 발생했는지 안했는지 까지 알수 있다.
		log.debug("-------- 응답 후 인터셉터실행 --------");
		log.debug("요청주소{}", request.getRequestURI());
		log.debug("에러메세지 {} ", ex != null ? ex.getMessage():"응답성공");
		log.debug("------------------------------");
		
		
		
	}
	
	
}
