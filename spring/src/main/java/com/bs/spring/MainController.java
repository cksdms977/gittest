package com.bs.spring;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 선언부에 controller를 선언하면 bean으로 등록할수 있음 spring에서 MVC중 Controller역할을 하는 클래스에 적용한다.
public class MainController {
	//Controller로 등록된 클래스는 클라이언트가 요청한 서비스를 
	// 진행하는 메소드(매핑메소드)를 선언한다.
	// 요청URL주소와 연결되는 메소드를 말한다
	
	//@RequestMapping어노테이션을 매핑메소드로 선언부에 선언한다.
	// 일반적으로 controller 메소드는 일반적으로 String값을 반환하게 설정함.
	// view선택해서 출력시킬때.....
	@RequestMapping("/")
	public String main(HttpServletRequest req, HttpSession session, HttpServletResponse res) {
		//메소드가 반환하는 값은 viewResolver Bean이 처리함.
		//등록된 InternalResourceResolver Bean은 반환된 문자열에 객체에 설정된 prefix, sufix를 붙여서 내부에서 화면을 찾는다
		// WEB-INF/views/리턴값.jsp
		//RequestDispatchser(WEB-INF/views/리턴값.jsp).forward
		
		//쿠키 추가하기
		Cookie c = new Cookie("testData", "cookiedata");
		c.setMaxAge(60*60*24);
		res.addCookie(c);
		
		session.setAttribute("sessionId", "admin");
		
		
		
		return "index";
	}
}
