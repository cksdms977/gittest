package com.bs.spring.bintest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;

// Pojo클래스를 생성하고, 이것을 선언부에서 bean으로 등록 할 수 있다.
// @Component, @Controller, @Service, @Repository
// 어노테이션을 이용해서 spring bean으로 등록
// @Component : 기본 spring bean으로 등록할 때 사용
// @Controller, @Service, Repository(DAO임) : mvc패턴에 의해 역할 지정된 클래스를 bean으로 등록할때 사용
@Component
@Data
public class FunctionalTest {
	private String name = "test";
	
//	@Autowired // 이렇게 출력할수 있음 근데 이렇게 거의 안함
	private Animal a;
	
//	// 생성자를 이용한 DI
//	public FunctionalTest(@Qualifier("dog") Animal a) {
//		this.a = a;
//	}
	//setter를 이용한 DI
	@Autowired
	public void setA(@Qualifier("dog") Animal a) {
		this.a = a;
	}
	public Animal getA() {
		return this.a;
	}
}
