package com.bs.helloboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SecurityController {
	@GetMapping("/loginpage")
	public String loginpage() {
		return "login";
	}
	@RequestMapping("/successLogin")
	public String successLogin() {
		log.debug("로그인성공");
		return "/";
	}
	@RequestMapping("/errorLogin")
	public String errorLogin() throws IllegalAccessException{
		throw new IllegalAccessException("잘못된접근입니디.");
		
	}
}
