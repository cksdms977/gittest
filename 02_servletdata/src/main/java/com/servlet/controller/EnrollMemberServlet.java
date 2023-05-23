package com.servlet.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "membertest", urlPatterns = {"/Member.do"})
public class EnrollMemberServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public EnrollMemberServlet() {
	}
	// 인코딩처리를 하자 !!!! // 인코딩처리를 하지 않으면 한글문자가 깨지게 된다.
	//HttpServletRequest.setCharacterEncodeing()메소드를 이용
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("UTF-8"); // 이렇게 인코딩처리를 해줘야 한글이 안깨진다.
	String id = req.getParameter("id");
	String password = req.getParameter("password");
	String name = req.getParameter("name");
	String nickname = req.getParameter("nickname");
	String email = req.getParameter("email");
	String hobbys = req.getParameter("hobby");
	String[] hobbys2 = req.getParameterValues("hobby");
	String married = req.getParameter("married");
	String info = req.getParameter("info");
	
	System.out.println("아이디 : " + id);
	System.out.println("패스워드 : " + password);
	System.out.println("닉네임 : " + nickname);
	System.out.println("이메일 : " + email);
	System.out.println("취미 : " + hobbys);
	Arrays.asList(hobbys2).stream().forEach(System.out::print);
	System.out.println();
	System.out.println("혼인여부 : " + married);
	System.out.println("소개 : " + info);
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp); // 이렇게 만들게 되면, this를 이용해서 doGet(req, resp)이렇게 불러오면 된다.
	}
	
	
}
