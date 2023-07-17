package com.bs.spring.member.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;


@RequestMapping("/member")
@SessionAttributes({"loginMember"}) // session으로 저장하고 싶은 키값을 이렇게 클래서에서 선언하면 session으로 출력해줄수 있다.
@Slf4j // logger를 생성해줌
@Controller
public class MemberController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private MemberService service;
	
	
	@RequestMapping("/enrollMember.do")
	public String enrollMember(@ModelAttribute("member") Member m) {
		return "member/enrollmember";
	}
	
	@RequestMapping(value="/insertMember.do", method = RequestMethod.POST)
	public String updateMember(@Validated Member m, BindingResult isResult, Model model) {
		if(isResult.hasErrors()) {
			//페이지전환
			return "member/enrollmember";
		}
			
		//패스워드를 암호화해서 처리하자
		String oripassword = m.getPassword();
//		System.out.println(oripassword);
		log.debug(oripassword);
		String encodePassword = passwordEncoder.encode(oripassword);
//		System.out.println(encodePassword);
		log.debug(encodePassword);
		m.setPassword(encodePassword);
		
		int result = service.insertMember(m);
		model.addAttribute("msg", result > 0?"회원을 등록했습니다.":"회원 등록을 실패했습니다.");
		model.addAttribute("loc", "/member/enrollMember.do");
		return "common/msg";
	}
	@RequestMapping("/login.do")
	public String loginMember(@RequestParam Map<String, Object> param, Model model) { //HttpSession session) {
//		param.put("userId",userId);
//		param.put("password", password);
		Member m = service.selectMemberById(param);
		
		//암호화된 값을 비교하기 위해서는 BCrptPasswordEncoder가 제공하는 메소드를 이용해야한다.
//		passwordEncoder.matches(null, null)
		if(m !=null && passwordEncoder.matches((String)param.get("password"), m.getPassword())) {
//		if(m !=null && m.getPassword().equals(param.get("password"))) {
//			session.setAttribute("loginMember", m);
			model.addAttribute("loginMember", m);
		}else {
			model.addAttribute("msg", "로그인실패");
			model.addAttribute("loc", "/");
			return "common/msg";
		}
		return "redirect:/";
	}
	
	@RequestMapping("/logout.do")
	//@SessionAttributes로 등록된 내용 삭제하기
	//SessionStatus객체를 이용해서 삭제
//	public String logout(HttpSession session, Model model) {
	public String logout(SessionStatus status) {
//		if(session != null) session.invalidate();
//		if(1==1) throw new IllegalArgumentException("잘못된 접근입니다.");
		if(!status.isComplete()) status.setComplete();
		return "redirect:/";
	}
	
	@RequestMapping("/mypage.do")
	public String Mypage(String userId, Model model) {
//		List<Member> list = service.mypage(userId);
		model.addAttribute("member", service.selectMemberById(Map.of("userId",userId)));
		return "member/mypage";
	}

	
	
	
}
