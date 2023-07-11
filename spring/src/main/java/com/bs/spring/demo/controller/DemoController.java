package com.bs.spring.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.demo.model.dto.Demo;

@Controller
public class DemoController {
	@RequestMapping("/demo/demo.do")
	public String demo() {
		return "demo/demo";
	}
	
	//매핑메소드선언하기
	//리턴값, 매개변수 알아보기
	// 1. 반환형
	// 1) String : ViewResolver에 view화면을 출력해줌 * 기본적으로 많이 사용
	// 2) void : HttpServletResponse객체로 직접 응답메세지를 작성할때 사용(파일업로드나/ 파일다운로드 할때 자주 사용)
	// 3) ModelAndView : 화면에 전달할 데이터와 view내용을 동시에 저장하는 객체 spring제공해줌
	// 4) Class타입(일반객체) : json으로 데이터를 반환할때 많이 사용, Restful하게 서브를 구성했을때 많이 사용
	//		*ResponesEntity<클래스타입>
	
	// 2. 매개변수로 선언할 수 있는 타입 
	// 1) HttpServletRequest : 서블릿에 그녀석... servlet처럼 사용이 가능
	// 2) HttpServletResponse : 서블릿에 그녀석... servlet처럼 사용이 가능
	// 3) HttpSession : 서블릿에 그녀석3... session값을 가져와서 대입해줌.
	// 4) java.util.Locale : 서버의 로케일 정보를 저장한 객체
	// 5) InputStream/Reder : 파일을 읽어 올때 사용하는 stream
	// 6) OutputStream.Writer : 파일 보낼때 사용하는 stream
	// 7) 기본자료형 변수 : 클라이언트가 보낸 parameter데이터랑 이름이 선언한 변수 이름이 동일하면 자동으로 매핑해줌
	// 		선언이름과 파라미터 이름이 다를 경우 @RequestParam어노테이션을 이용해서 연결할 수 있음
//			@RequestParam은 매핑, 기본값설정, 필수값여부를 설정
	// 8) 클래스변수 : Command라고 함, parameter데이터를 필드에 넣어서 객체를 전달
	//		*parameter 이름과 필드명이 같은 데이터를 대입해줌.
	// 9) java.uilt.Map : @RequestParam 어노테이션이랑 같이 사용, parameter값을 Map방식으로 저장을 함
	// 10) @RequestHeader 와 기본자료형을 작성하면 header의 정보를 받을 수 있음
	// 11) @CookieValue(name값)와 기본자료형을 작성하면 Cookie에 저장된 값을 받을수 있다.
	// 12) Model : request와 비슷하게 데이터를 key/value형식으로 저장할수 있는 객체
	// 13) ModelAndView : model과 view를 동시에 저장하는 객체
	
	// 추가메소드 어노테이션
	// @ResponseBody -> Rest 방식으로 클래스를 json으로 전송할때
	// @RequestBody -> json방식으로 전송된 parameter를 클래스로 받을때 사용
	// @GetMapping, @PostMapping, @DeleteMapping....
	
	// 서블릿방식으로 매핑메소드 이용하기
	@RequestMapping("/demo/demo1.do")
	public void demo1(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		System.out.println(req);
		System.out.println(res);
		String devName = req.getParameter("devName");
		int devAge = Integer.parseInt(req.getParameter("devAge"));
		String devGender = req.getParameter("devGedner");
		String devEmail =req.getParameter("devEmail");
		String [] devLang = req.getParameterValues("devLang");
		System.out.println(devName + " " + devAge + " " + devGender + " " + devEmail);
		for(String l : devLang) {
			System.out.println(l);
		}
//		res.setContentType("text/html;charset=utf-8");
//		PrintWriter out = res.getWriter();
//		out.print("<h2>" + devName + " " + devAge + " " + devGender + " " + devEmail + "</h2>");
		Demo d = Demo.builder().devName(devName).devAge(devAge).devEmail(devEmail).devGender(devGender).devLang(devLang).build();
		req.setAttribute("demo", d);
		req.getRequestDispatcher("/WEB-INF/views/demo/demoResult.jsp").forward(req, res);
	}
	
	
}