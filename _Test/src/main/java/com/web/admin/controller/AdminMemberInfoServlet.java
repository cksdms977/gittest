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

/**
 * Servlet implementation class AdminMemberInfoServlet
 */
@WebServlet("/admin/memberList.do")
public class AdminMemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberInfoServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		DB의 member테이블에 저장된 전체회원을 가져와 화면에 출력해주는 기능 
      
//		페이징 처리하기
		int cPage = 0;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e){
			cPage = 1;
		}
		int numPerpage = 10;
//		이 값을 매개변수로 던져준다.
		
//		1. DB에서 member테이블에 있는 데이터가져오기
		List<MemberDto> memberlist = new AdminMemberService().membermanagement(cPage, numPerpage);
		System.out.println(memberlist);
		
//		2. DB에서 가져온 데이터저장 (화면출력)
		request.setAttribute("membermanagment", memberlist);
//		- 페이지바 구성 여기서 페이지바를 만들어준다. 
//		1) DB에 저장된 전체 데이터 수를 가져온다.
		int totalData = new AdminMemberService().selectMemberCount();
		
//		2) 전체 페이지수를 계산하기 * 소수점 주의!!
		int totalPage = (int)Math.ceil((double)totalData/numPerpage);
		
//		3) 페이지바 시작번호 계산하기
		int pageBarSize = 5;
		int pageNo = ((cPage - 1)/pageBarSize) * pageBarSize + 1;
		int pageEnd = pageNo + pageBarSize - 1;
//		- 4) 페이지바를 구성하는 html저장하기
//		이전 표시하기
		String pageBar = "";
		if(pageNo == 1) {
			pageBar += "<span>[이전]</span>";
		}else {
			pageBar += "<a href='"+request.getRequestURI()+"?cPage="+(pageNo-1)+"'>[이전]</a>";
		}
//		선택할 페이지 번호 출력하기
		while(!(pageNo > pageEnd || pageNo > totalPage)) {
			if(pageNo == cPage) {
				pageBar +="<span>" + pageNo + "</span>";
			}else {
				pageBar += "<div class='nextbutton'><a href='"+request.getRequestURI() +"?cPage="+pageNo+"'>"+pageNo+"</a></div>";
			}
			pageNo ++;
		}
		
		// 다음출력 
		if(pageNo > totalPage) {
			pageBar += "<span>[다음]</span>";
		}else {
			pageBar += "<a href='"+request.getRequestURI()+"?cPage="+pageNo+"'>[다음]</a>";
		}
		request.setAttribute("pageBar", pageBar);
		
//		3. 출력할 화면을 선택(이동)
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
