package com.mybatis.model.service;

import java.util.List;
import java.util.Map;

import com.mybatis.model.vo.Department;
import com.mybatis.model.vo.Employee;

public interface EmpService {
	List<Employee> selectEmployeeAll();
	List<Employee> searchEmployee(Map<String, Object> param);
	List<Department> selectAllDept();
}
