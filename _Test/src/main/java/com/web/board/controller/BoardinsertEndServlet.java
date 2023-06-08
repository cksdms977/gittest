package com.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.web.board.dto.BoardDto;
import com.web.board.service.BoardService;

/**
 * Servlet implementation class BoardinsertEndServlet
 */
@WebServlet("/board/boardinsertend.do")
public class BoardinsertEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardinsertEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "잘못된접근입니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
		String path = getServletContext().getRealPath("/upload/board");
		
		int maxSize = 1024*1024*200;
		String encode = "UTF-8";
		DefaultFileRenamePolicy dfr = new DefaultFileRenamePolicy();
		MultipartRequest mr = new MultipartRequest(request, path, maxSize, encode, dfr);
		
		String boardtitle = mr.getParameter("board_title");
		String boardwriter = mr.getParameter("board_writer");
		String boardfile = mr.getParameter("board_file");
		String boardcontent = mr.getParameter("board_content");
		
		BoardDto b = BoardDto.builder()
					.boardTitle(boardtitle)
					.boardWriter(boardwriter)
					.boardRenamed(boardfile)
					.boardContent(boardcontent).build();
		int insertboard = new BoardService().insertboard(b);
		
		String msg ="", loc="";
		if(insertboard > 0) {
			msg = "공지사항이 등록되었습니다.";
			loc = "/board/boardList.do";
		}else {
//			입력 실패에 대한 메세지
			msg = "공지사항등록이 취소되었습니다. :( \n다시 시도하세요!";
			loc = "/board/selectboardview.do";
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
