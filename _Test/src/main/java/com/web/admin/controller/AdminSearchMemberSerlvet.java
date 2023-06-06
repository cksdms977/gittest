package com.web.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.admin.service.AdminMemberService;
import com.web.member.model.dto.MemberDto;
import com.web.member.model.service.MemberSerivce;

/**
 * Servlet implementation class AdminSearchMemberSerlvet
 */
@WebServlet("/admin/searchMember")
public class AdminSearchMemberSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSearchMemberSerlvet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selecttype = request.getParameter("selecttype");
		String searchkeyword = request.getParameter("searchKeyword");
		int index = 0;
		
		if(selecttype.equals("userId")) {
			index = 0;
		}else if(selecttype.equals("userName")) {
			index = 1;
		}else if(selecttype.equals("gender")) {
			index = 2;
		}
		List<MemberDto> memberList = new AdminMemberService().searchMemberList(searchkeyword, index);
		
		request.setAttribute("memberList", memberList);
		request.getRequestDispatcher("/views/admin/adminmemberinfo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
