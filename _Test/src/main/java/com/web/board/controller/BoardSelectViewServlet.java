package com.web.board.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.dao.BoardComment;
import com.web.board.dto.BoardDto;
import com.web.board.service.BoardService;

/**
 * Servlet implementation class BoardSelectViewServlet
 */
@WebServlet("/board/selectboardview.do")
public class BoardSelectViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSelectViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int number = Integer.parseInt(request.getParameter("BOARD_NO"));
//		BoardDto selectboard = new BoardService().selectboardview(number);
		
		Cookie[] cookies = request.getCookies();
		String boardRead = "";
		boolean isRead = false;
		if(cookies != null) {
			for (Cookie c : cookies) {
				if(c.getName().equals("boardRead")){
					boardRead = c.getValue();
					if(boardRead.contains("|" + number + "|")) {
						isRead = true;
					}
					break;
				}
			}
		}
		if(!isRead) {
//		그냥 하면 키 값이 중복되서 이전값을 없애고 계속 덮어쓰기가 되버림 분기처리 해줘야함 
		Cookie c = new Cookie("boardRead", boardRead + "|" + number + "|");
		c.setMaxAge(60*60);
		response.addCookie(c);
		}
		
		BoardDto selectboard = new BoardService().selectboardview(number, isRead);
		
//		댓글가져오기 로직 
		List<BoardComment> boardcomment = new BoardService().selectBoardComment(number);
		request.setAttribute("boardcomment", boardcomment);
//		원래 여기부분 분기처리 해주는게 좋음 
		request.setAttribute("selectboard", selectboard);
		request.getRequestDispatcher("/views/board/selectboardview.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
