package com.web.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.notice.dto.NoticeDto;
import com.web.notice.service.NoticeService;

/**
 * Servlet implementation class NoticeWriterEndServlet
 */
@WebServlet("/notice/noticewriterend.do")
public class NoticeWriterEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriterEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String noticetitle = request.getParameter("notice_title");
		String noticewriter = request.getParameter("notice_writer");
		String noticefilepath = request.getParameter("notice_filepath");
		String noticecontent = request.getParameter("notice_content");
		
		NoticeDto n = NoticeDto.builder()
					  .noticeTitle(noticetitle)
					  .noticeWriter(noticewriter)
					  .noticefilePath(noticefilepath)
					  .noticeContent(noticecontent).build();
		
		int insertnotice = new NoticeService().insertNotice(n);
		
		String msg = "";
		String loc = "";
		if(insertnotice > 0) {
//			입력 성공에 대한로직
			msg = "공지사항이 등록되었습니다.";
			loc = "/";
		}else {
//			입력 실패에 대한 메세지
			msg = "공지사항등록이 취소되었습니다. :( \n다시 시도하세요!";
			loc = "/notice/noticewriter.do";
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
