package com.bs.spring.memo.model.conroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bs.spring.memo.model.dto.Memo;
import com.bs.spring.memo.model.service.MemoService;

import lombok.extern.slf4j.Slf4j;
@RequestMapping("/memo")
@SessionAttributes({"loginMember"})
@Slf4j
@Controller
public class MemoController {
	@Autowired
	private MemoService service;
	
//	@Autowired
//	public MemoController(MemoService service) {
//		this.service=service;
//	} 위에 serivce에 autowired를 안해소 이렇게 매소들 만들어서 선언해주면 된다.
	
	
	@RequestMapping("/memolist.do")
	public String memolist(Model model) {
		List<Memo> memos = service.MemoList();
		log.debug("{}", memos);
		model.addAttribute("memos", memos);
		return "memo/memolist";
	}
	@RequestMapping(value="/insertmemo.do", method = RequestMethod.POST)
	public String insertMemo(Memo m, Model model) {
		int result = service.insertMemo(m);
		log.debug("{}", result);
			if(result==0) {
			return "common/msg";
		}
		return "redirect:/memo/memolist.do";
	}
}
