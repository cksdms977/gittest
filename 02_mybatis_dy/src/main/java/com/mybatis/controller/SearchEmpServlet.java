package com.mybatis.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mybatis.model.service.EmpService;
import com.mybatis.model.service.EmpServiceImpl;
import com.mybatis.model.vo.Employee;

/**
 * Servlet implementation class SearchEmpServlet
 */
@WebServlet("/searchEmp.do")
public class SearchEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpService service;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEmpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		String gender = request.getParameter("gender");
		String salflag = request.getParameter("salFlag");
	
		service = new EmpServiceImpl();
		Map<String, Object> param = new HashMap<>();
		param.put("type", type);
		param.put("keyword", keyword);
		param.put("gender", gender);
		param.put("salflag", salflag);
		param.put("salary", Integer.parseInt(request.getParameter("salary").equals("")?"0":request.getParameter("salary")));
		param.put("deptCode", request.getParameterValues("deptCode"));
		param.put("jobCode", request.getParameterValues("jobCode"));
		param.put("dateFlag", request.getParameter("dateFlag"));
		param.put("hireDate", request.getParameter("hiredate"));
		List<Employee> employees = service.searchEmployee(param);
		System.out.println(employees);
		request.setAttribute("employees", employees);
		request.getRequestDispatcher("/views/employee.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
