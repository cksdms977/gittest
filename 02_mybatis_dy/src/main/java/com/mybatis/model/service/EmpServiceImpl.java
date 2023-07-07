package com.mybatis.model.service;

import static com.mybatis.common.SessionTemplate.getSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mybatis.model.dao.EmpDao;
import com.mybatis.model.dao.EmployeeDaoImpl;
import com.mybatis.model.vo.Department;
import com.mybatis.model.vo.Employee;

public class EmpServiceImpl implements EmpService {
	private EmpDao dao = new EmployeeDaoImpl();
	@Override
	public List<Employee> selectEmployeeAll() {
		SqlSession session = getSession();
		List<Employee> list = dao.selectEmployeeAll(session);
		session.close();
		return list;
	}
	@Override
	public List<Employee> searchEmployee(Map<String, Object> param) {
		SqlSession session = getSession();
		List<Employee> list = dao.searchEmp(session, param);
		session.close();
		return list;
	}	
	@Override
	public List<Department> selectAllDept() {
		SqlSession session = getSession();
		List<Department> list = dao.selectAllDept(session);
		session.close();
		return list;
}
} 
