package com.mybatis.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// 객체간에 연관관계가 있을때 toString을 서로 호출하는 오류가 뜸 그래서 하나는 tostring 을 빼줘야 한다.
@ToString(exclude= {"employees"}) 
public class Department {
	private String deptId;
	private String deptTitle;
	private String locationId;
	
	private List<Employee> employees;
}
