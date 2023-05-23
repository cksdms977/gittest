package com.servlet.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherViewServlet
 */
@WebServlet("/dispatcherView.do")
public class DispatcherViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DispatcherViewServlet 실행!");		
		String name=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("number"));
		double height=Double.parseDouble(request.getParameter("height"));
		String color=request.getParameter("color");
		String[] animals2=request.getParameterValues("animal");
		String lunch=request.getParameter("lunch");
		String info=request.getParameter("info");

		// setAttribute로 저장된 데이터 가져오기
		// HttpServletRequest.getAttribute("key값")메소드를 이용
		String data = (String) request.getAttribute("testData"); // 여기는 Object로 구성됨 
		// Object이기 때문에 이렇게 형변환을 해줘야한다.
		
		response.setContentType("text/html;charset=utf-8"); // text/html 에 작성한게 type을 정한것, 그 뒤에 작성한게 utf-8로 인코딩한값
		// 2. 응답데이터 보내기
		// 1) 문자열 데이터 : 문자열 스트림으로 전송 -> getWriter();
		// 2) 바이너리데이터 : 파일스트림으로 전송 -> getOutputStream();
		PrintWriter out = response.getWriter();
		// 3. 원하는 데이터 전송하기
//		out.write("<h3>내가 만든 첫 응답페이지</h3>");
		String html = "<html>";
		html += "<head>";
		html += "<title>개인취향테스트</title>";
		html += "</head>";
		html += "<body>";
		html += "<h1>getAttribute값 : " + data  + "</h1>";
		html += "<h3>개인취향결과</h3>";
		html += "<h4>" + name + "님의 개인취향 확인결과</h4>";
		html += "<h4>당신의 이름은 " + name + "이고 나이는 " + age + "살 이고, ";
		html += "키는 " + height + "cm입니다.</h4>";
		html += "<h4>좋아하는 색은 <span style = 'color: " + color + "'>" + color + "</span>";
		html += "입니다.</h4>";
		html += "<ul>좋아하는 동물";
		for(String animal:animals2) {
			String src = "";
			switch(animal) {
			case "강아지" : src = "https://s3.ap-northeast-2.amazonaws.com/elasticbeanstalk-ap-northeast-2-176213403491/media/magazine_img/magazine_262/%EC%8D%B8%EB%84%A4%EC%9D%BC.jpg";break;
			case "고양이" : src = "https://images.mypetlife.co.kr/content/uploads/2023/01/03112051/AdobeStock_156531656-1024x704.jpeg"; break;
			case "펭귄" : src = "https://pbs.twimg.com/media/Fuhjk07akAA9zgC.jpg:large"; break;
			case "기린" : src ="https://img.khan.co.kr/news/2018/07/04/l_2018070401000545500042672.jpg"; break;
  			}
			html+="<li><img src='"+src+"' width=200 height=200></li>";
		}
		html += "</ul>";
		html += "<p>오늘의 점심은 " + lunch + "입니다. </p>";
		html += "<h3>당신은 " + info + "</h3>";
		html += "</body>";
		html += "</html>";
		out.write(html);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
