package com.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckDataServlet
 */
@WebServlet("/checkData.do")
public class CheckDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestData = (String)request.getAttribute("requestdata");
		HttpSession session = request.getSession();
		String sessionData = (String)session.getAttribute("sessiondata");
		ServletContext context = getServletContext();
		String contextData = (String)context.getAttribute("contextdata");
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String html = "<h3>request : " + requestData + "</h3>";
		html += "<h3>session : " + sessionData + "</h3>";
		html += "<h3>context : " + contextData + "</h3>";
		html += "<button onclick=\"location.assign('/02_servletdata/checkData.do');\">checkdata재요청</button>";
		html += "<button onclick=\"location.assign('/02_servletdata/deleteSession.do');\">deletesession삭제</button>";

		out.write(html);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
