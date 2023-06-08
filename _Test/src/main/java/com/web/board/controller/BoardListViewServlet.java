package com.web.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.dto.BoardDto;
import com.web.board.service.BoardService;

/**
 * Servlet implementation class BoardListViewServlet
 */
@WebServlet("/board/boardList.do")
public class BoardListViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		int cPage, numPerpage;
//		try {
//			cPage = Integer.parseInt(request.getParameter("cPage"));
//		}catch(NumberFormatException e){
//			cPage = 1;
//		}
//		try {
//			numPerpage = Integer.parseInt(request.getParameter("numPerpage"));
//		}catch(NumberFormatException e){
//			numPerpage = 10;
//		}
//		
//		String pageBar = "";
//		int totalData = new NoticeService().
//		
		List<BoardDto> boardList = new BoardService().BoardList();
		System.out.println(boardList);
		
		request.setAttribute("boardList", boardList);
		request.getRequestDispatcher("/views/board/boardlist.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
