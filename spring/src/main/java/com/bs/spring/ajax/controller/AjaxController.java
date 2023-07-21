package com.bs.spring.ajax.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.common.exception.AthenticationException;
import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ajax")
public class AjaxController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/basicTest.do")
	public void basic(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Board b = Board.builder().boardTitle("냉무").boardContent("냉무").build();
		// 타입을 정해서 이렇게 보낼수 있음
//		res.setContentType("text/csv;charset=utf-8");
//		res.getWriter().write("test");
		
		ObjectMapper mapper = new ObjectMapper();
		res.setContentType("application/json;charset=utf-8");
		res.getWriter().write(mapper.writeValueAsString(b)); // ObjectMapper 객체를 json으로 요청할수있다.
	}
	
	// return값에 반환할 객체를 선언
	//@ResponseBody -> json 으로 반환할 수 있게 처리
	@GetMapping("/converter")
	@ResponseBody
	public Board convertTest() {
		return Board.builder().boardTitle("spring좋다!").boardContent("하하하").build();
	}
	@PostMapping("checkuserId.do")
	@ResponseBody
	public Member checkuserId(@RequestParam Map param) {
		log.info("{}", param);
		return memberService.selectMemberById(param);
	}
	
	@GetMapping("/basic2")
	public String basic2() {
		return "demo/demo";
	}
	@GetMapping("/memberlist.do")
	@ResponseBody
	public List<Member> memberlist()  {
//		if(1==1) throw new AthenticationException("권한애러발생0");
		List<Member> list = memberService.memberlist();
		log.debug("{}", list);
		return list;
	}
	@PostMapping("/insertData.do")
	public Member insertData(@RequestBody Member m) {
		log.info("{}", m);
		return m;
	}
	
	//REST API, RESTFul -> session관리안해!(stateless)
	//URL을 설정할때 간편하게 서비스를 알아볼 수 있는 방식으로 구현하자
	//URL주소를 설정할때 행위에 대한 표현을 빼자 -> method를 보고 결정하자
	//method
	// GET : Data를 조회하는 서비스는 GET
	// GET localhost:9090/spring/member/{id}1||admin -> 회원 1명 조회
	// POST : Data를 저장하는 서비스 
	// PUT : Data를 수정하는 서비스
	// DELETE : Data를 삭제하는 서비스 
	// URL주소를 설정할때는 명사로 작성한다.
	// 예) 회원을 관리하는 서비스
	// GET localhost:9090/spring/member -> 전체회원조회
	// POST localhost:9090/spring/member -> 회원추가
	// PUT localhost:9090/spring/member -> 회원수정
	// DELETE localhost:9090/spring/member -> 회원삭제
}
