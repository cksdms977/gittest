package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.member.model.dto.MemberDto;
import com.web.member.model.service.MemberSerivce;


/**
 * Servlet implementation class UpdateMemberServlet
 */
@WebServlet("/member/memberUpdate.do")
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDto m = MemberDto.builder()
				.userId(request.getParameter("userId"))
				.userName(request.getParameter("userName"))
				.age(Integer.parseInt(request.getParameter("age")))
				.gender(request.getParameter("gender").charAt(0))
				.email(request.getParameter("email"))
				.phone(request.getParameter("phone"))
				.address(request.getParameter("address"))
				.hobby(request.getParameterValues("hobby")).build();
		
		int result = new MemberSerivce().updatemember(m);
		
		String msg = "", loc="";
		if(result > 0) {
			msg = "회원정보가 수정되었습니다.";
			loc="/";
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", new MemberSerivce().duplicateid(m.getUserId()));
		}else {
			msg = "회원정보 수정실패했습니다.다시 시도하세요";
//			
			loc ="/member/selectmemberinfo.do?userId="+m.getUserId();
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
