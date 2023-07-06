package com.mybatis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mybatis.model.service.EmpService;
import com.mybatis.model.service.EmpServiceImpl;
import com.mybatis.model.vo.Employee;

/**
 * Servlet implementation class SelectEmployeeAllServlet
 */
@WebServlet("/selectemployeeall")
public class SelectEmployeeAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpService service;
	
	
    /**
     * Default constructor. 
     */
    public SelectEmployeeAllServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List<Employee> employees = new EmployeeService().selectEmployeeAll();
//		System.out.println(employees);
//		request.setAttribute("employees", employees);
//		request.getRequestDispatcher("/views/employee.jsp");
		
		service = new EmpServiceImpl();
		List<Employee> employees = service.selectEmployeeAll();
//		System.out.println(employees);
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
