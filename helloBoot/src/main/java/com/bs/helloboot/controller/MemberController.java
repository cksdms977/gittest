package com.bs.helloboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.helloboot.service.MemberService;
import com.bs.helloboot.service.MemberServiceImpl;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberService service;
	public MemberController(MemberService service) {
		this.service=service;
	}
	
//	@GetMapping("/")
//	public String index() {
//		return "index";
//	}
	@RequestMapping("/memberAll")
	public String selectMemberAll(Model model) {
		model.addAttribute("members", service.selectMemberAll());
		return "member/memberList";
	}
}
