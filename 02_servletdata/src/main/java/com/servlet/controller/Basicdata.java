package com.servlet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Basicdata extends HttpServlet {


	private static final long serialVersionUID = -2743344111597204971L;
	
	public Basicdata() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("서블릿 샐행");
//		클라이언트가 전송한 데이터 받아오기
// 		HttpServletRequest객체가 제공하는 메소드를 이용해서 받아올수 있다.
//		getParameter()메소드 -> 클라이언트가 보낸 데이터 한개를 받아온다.
		String data = request.getParameter("testdata");//String
		System.out.println(data);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
//		System.out.println("");
	}
	
	
	
	
}
