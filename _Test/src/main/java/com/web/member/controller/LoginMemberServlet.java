package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.member.model.dto.MemberDto;
import com.web.member.model.service.MemberSerivce;

/**
 * Servlet implementation class LoginMemberServlet
 */
@WebServlet(name="login",urlPatterns = "/login.do")
public class LoginMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String userId = request.getParameter("userId");
			String passoword = request.getParameter("password");
			
			// 아이디 저장 체크
			String saveId = request.getParameter("saveId");
			if(saveId != null) {
				Cookie c = new Cookie("saveId", userId);
				c.setMaxAge(60*60*24*7);
				response.addCookie(c);
			}else {
				Cookie c = new Cookie("saveId", "");
				c.setMaxAge(0);
				response.addCookie(c);
			}
			
			
			MemberDto loginmember = new MemberSerivce().login(userId, passoword);
			
			if(loginmember != null) {
				HttpSession session = request.getSession();
				session.setAttribute("loginmember", loginmember);
				response.sendRedirect(request.getContextPath());
				
			}else {
				request.setAttribute("msg","아이디,패스워드가 일치하지않습니다");
				request.setAttribute("loc", "/");
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			}
			
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
