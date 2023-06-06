package com.web.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.notice.dto.NoticeDto;
import com.web.notice.service.NoticeService;

/**
 * Servlet implementation class NoticeDetaileViewServlet
 */
@WebServlet("/notice/noticedetaileview.do")
public class NoticeDetaileViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetaileViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int number = Integer.parseInt(request.getParameter("NOTICE_NO"));
		NoticeDto detailenotice = new NoticeService().selectnoticeview(number);
		
		if(detailenotice != null) {
		HttpSession session = request.getSession();
		session.setAttribute("detailenotice", detailenotice);
		request.getRequestDispatcher("/views/notice/detailenoticeview.jsp").forward(request, response);
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
