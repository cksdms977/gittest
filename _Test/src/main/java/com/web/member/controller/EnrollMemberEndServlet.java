package com.web.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.member.model.dto.MemberDto;
import com.web.member.model.service.MemberSerivce;

/**
 * Servlet implementation class EnrollMemberEndServlet
 */
@WebServlet("/member/enrollendmember.do")
public class EnrollMemberEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollMemberEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String name = request.getParameter("userName");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		String[] hobby = request.getParameterValues("hobby");
		
		MemberDto m = new MemberDto();
		m.setUserId(userId);
		m.setPassword(password);
		m.setUserName(name);
		m.setAge(age);
		m.setEmail(email);
		m.setGender(gender.charAt(0));
		m.setPhone(phone);
		m.setAddress(address);
		m.setHobby(hobby);
		
		int enrollMember = new MemberSerivce().enrollerMember(m);
		
		String msg = "";
		String loc = "";
		if(enrollMember > 0 ) {
//			입력 성공에 대한로직
			msg = "회원가입을 축하드립니다.!";
			loc = "/";
		}else {
//			입력 실패에 대한 메세지
			msg = "회원가입에 실패하였습니다. :( \n다시 시도하세요!";
			loc = "/member/enrollMember.do";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
