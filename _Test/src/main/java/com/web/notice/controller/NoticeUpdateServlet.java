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
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet("/notice/updatenotice.do")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeDto n = NoticeDto.builder()
				.noticeTitle(request.getParameter("notice_title"))
				.noticeWriter(request.getParameter("notice_wirter"))
				.noticefilePath(request.getParameter("notice_filepath"))
				.noticeContent(request.getParameter("notice_content")).build();
		int number = Integer.getInteger(request.getParameter("NOTICE_NO"));
		
		int result = new NoticeService().updatenotice(n, number);
		
		String msg = "", loc="";
		if(result > 0) {
			msg = "공지사항이 수정되었습니다.";
			loc="/";
			HttpSession session = request.getSession();
			session.setAttribute("updatenotice", new NoticeService().selectnoticeview(n.getNoticeNo()));
		}else {
			msg = "공지사항 수정을 하지 못했습니다. 다시 시도하세요";
//			
			loc ="/notice/noticedetaileview.do?noticeNo="+n.getNoticeNo();
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
