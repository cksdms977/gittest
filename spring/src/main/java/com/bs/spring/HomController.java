package com.bs.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.bintest.Animal;

@Controller
public class HomController {
	//springbean으로 동록된 객체는 필드로 가져와 사용할 수 있음
	@Autowired // Animal에 있는 개체를 넣어줘 라는 뜻
	private Animal a;
	
	@RequestMapping("/")
	public String home() {
		System.out.println(a);
		return "index";
	}
}
