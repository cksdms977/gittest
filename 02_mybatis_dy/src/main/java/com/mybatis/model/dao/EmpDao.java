package com.mybatis.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mybatis.model.vo.Department;
import com.mybatis.model.vo.Employee;

public interface EmpDao {
	List<Employee> selectEmployeeAll(SqlSession session);
	
	List<Employee> searchEmp(SqlSession session, Map<String, Object> param);
	
	List<Department> selectAllDept(SqlSession session);
}
