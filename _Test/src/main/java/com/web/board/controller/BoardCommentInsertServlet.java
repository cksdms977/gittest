package com.web.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.dao.BoardComment;
import com.web.board.service.BoardService;

/**
 * Servlet implementation class BoardCommentInsertServlet
 */
@WebServlet("/board/insertComment.do")
public class BoardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardComment bc = BoardComment.builder()
				.boardRef(Integer.parseInt(request.getParameter("boardRef")))
				.level(Integer.parseInt(request.getParameter("level")))
				.boardCommentWriter(request.getParameter("boardCommentWriter"))
				.boardCommentContent(request.getParameter("content"))
				.boardCommentRef(Integer.parseInt(request.getParameter("boardCommentRef")))
				.build();
		
		int result = new BoardService().insertboardComment(bc);
		String view;
		if(result > 0) {
			view =request.getContextPath()+"/board/selectboardview.do?BOARD_NO="+bc.getBoardRef();
			response.sendRedirect(view);
		}else {
			request.setAttribute("msg", "댓글등록 실패");
			request.setAttribute("loc", "/board/selectboardview.do?BOARD_NO="+bc.getBoardRef());
			view = "/views/common/msg.jsp";
			request.getRequestDispatcher(view).forward(request, response);
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
