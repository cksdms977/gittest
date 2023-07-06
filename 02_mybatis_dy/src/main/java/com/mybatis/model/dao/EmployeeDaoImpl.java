package com.mybatis.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mybatis.model.vo.Employee;

public class EmployeeDaoImpl implements EmpDao {

	@Override
	public List<Employee> selectEmployeeAll(SqlSession session) {
		return session.selectList("employee.selectEmployeeAll");
	}

	@Override
	public List<Employee> searchEmp(SqlSession session, Map<String, Object> param) {
		return session.selectList("employee.searchEmp", param);
	}
}
