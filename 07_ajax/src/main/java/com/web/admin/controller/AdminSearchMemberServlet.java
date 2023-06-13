package com.web.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.admin.model.service.AdminMemberSerivce;
import com.web.member.model.vo.Member;


/**
 * Servlet implementation class AdminSearchMemberServlet
 */
@WebServlet("/admin/searchMember")
public class AdminSearchMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSearchMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("searchType");
		String keyword = request.getParameter("searchKeyword");
		
		int cPage, numPerPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage = 1;
		}
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		}catch(NumberFormatException e) {
			numPerPage = 5;
		}
		
		List<Member> members = new AdminMemberSerivce().selectMemberByKeyword(type, keyword, cPage, numPerPage);
		request.setAttribute("members", members);
		
		String pageBar = "";
		int totalData = new AdminMemberSerivce().selectMemberByKeywordCount(type, keyword);
		int totalPage = (int)Math.ceil((double)totalData/numPerPage);
		int pageBarSize = 5;
		int pageNo = ((cPage - 1)/pageBarSize) * pageBarSize + 1;
		int pageEnd = pageNo + pageBarSize - 1;
		
		if(pageNo == 1) {
			pageBar += "<span>[이전]</span>";
		}else {
			pageBar += "<a href='"+request.getRequestURI()+"?searchType=" + type + "&searchKeyword=" + keyword + "&cPage=" + (pageNo-1) + "&numPerPage="+numPerPage +"'>[이전]</a>";
			
		}
		
		while(!(pageNo > pageEnd || pageNo > totalPage)) {
			if(pageNo == cPage) {
				pageBar +="<span>" + pageNo + "</span>";
			}else { 
				pageBar += "<a href='"+request.getRequestURI()+"?searchType=" + type + "&searchKeyword=" + keyword + "&cPage=" + (pageNo) + "&numPerPage="+pageNo +"'>"+pageNo+"</a>";

			}
			pageNo ++;
		}
		if(pageNo > totalPage) {
			pageBar += "<span>[다음]</span>";
		}else {
			pageBar += "<a href='"+request.getRequestURI()+"?searchType=" + type + "&searchKeyword=" + keyword + "&cPage=" + (pageNo) + "&numPerPage="+pageNo +"'>[다음]</a>";

		}
		request.setAttribute("pageBar", pageBar);

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
