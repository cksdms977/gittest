package com.servlet.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestDisPatcherTestServlet
 */
@WebServlet("/requestdispatchertest.do")
public class RequestDisPatcherTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestDisPatcherTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		요청내용을 다른 서블릿으로 전환하기.
		System.out.println("requestDispatcher 서블릿 실행"); // 여기는 jdbc db데이터 쓰는 로직을 작성하고 여기부분이 바로 밑으로 반환해주는것이다.
		
		// 여기서는 데이터를 저장하는 역항을 함
		// HttpServletRequest객체가 제공하는 setAttribute()메소드를 이용한다. Db에서 쓴 데이터를 저장할때 쓴다.
		// key : value형식으로 저장함.
		// setAttribute("key", value:object);
		request.setAttribute("testData", "개인취향테스트에 오신걸 환영합니다."); // db랑 연결안했기에 서버측에서 임의로 만든거임
		
		
		
		//RequestDispatcher객체를 이용한 서블릿 이용하기
		// HttpServletRequest.getRequestDispathcher("서블릿 || jsp주소");를 이용
		RequestDispatcher rd = request.getRequestDispatcher("/dispatcherView.do");// 위에 잇는 부분은 jdbc인 db데이터를 가져오는 코드이고 여기부분은 화면출력하는 로직
		rd.forward(request, response); // 위에 잇는 request, response을 가져오는것 // 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
