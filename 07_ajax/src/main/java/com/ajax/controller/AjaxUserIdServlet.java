package com.ajax.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.admin.model.service.AdminMemberSerivce;
import com.web.member.model.vo.Member;

/**
 * Servlet implementation class AjaxUserIdServlet
 */
@WebServlet("/useridmemberinfo.do")
public class AjaxUserIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxUserIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Member> memberinfo = new AdminMemberSerivce().membermanagement(1, 100);
//		memberinfo.stream().forEach(System.out::println);
		String resultData = memberinfo.stream().map(e->e.toString()).collect(Collectors.joining("\n"));
		System.out.println(resultData);
		
		response.setContentType("text/csv;charset=utf-8");
		response.getWriter().print(resultData);
//		request.setAttribute("memberinfo", memberinfo);
//		request.getRequestDispatcher("/views/memberTable.jsp").forward(request, response);
		
		// csv 방식으로 데이터를 보내는 방식
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
