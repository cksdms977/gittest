package com.bs.spring.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.jpa.model.service.JpaService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/jpa")
@Slf4j
public class JpaController {
	private JpaService service;
	public JpaController(JpaService service) {
		this.service=service;
	}
	
	@GetMapping("/basicTest.do")
	public String basicTest() {
		service.basictest();
		return "redirect:/";
	}
	
	@GetMapping("/manytoone.do")
	public String manytoone() {
		service.manytoone();
		return "redirect:/";
	}
	@GetMapping("/onetoone.do")
	public String onetoone() {
		service.insertStudent();
		return "redirect:/";
	}
	
	@GetMapping("/entitydelete.do")
	public String deletStudent(long no) {
		service.deleteStudent(no);
		return "redirect:/";
	}
}
