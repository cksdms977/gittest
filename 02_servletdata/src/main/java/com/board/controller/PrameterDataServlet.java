package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PrameterDataServlet
 */
@WebServlet(name = "boardparamdata", urlPatterns = {"/insertboard.do"})
public class PrameterDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrameterDataServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String info = request.getParameter("info");
		int boardNo = (int)Math.random();
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String html = "<html>";
		html += "<head>";
		html += "<title>게시글</title>";
		html += "</head>";
		html += "<body>";
		html += "<h3>게시글 번호 : " + boardNo + "</h3>";
		html += "<h3>게시글 제목 : " + title  + "</h3>";
		html += "<h4>작성자 : " + writer + "</h4>";
		html += "<h4>내용 : " + info + "</h4>";
		html += "</body>";
		html += "</html>";
		out.write(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
