package com.bs.spring.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.common.PageFactory;

import lombok.extern.slf4j.Slf4j;
@RequestMapping("/board")
@Slf4j
@Controller
public class BoardController {
	@Autowired
	private BoardService service;
	
	
	@RequestMapping("/boardlist.do")
	public String boardlist(@RequestParam(value="cPage", defaultValue = "1")int cPage,
							@RequestParam(value="numPerpage", defaultValue = "5")int numPerpage, Model model) {
		//페이지에 맞는 데이터를 가져와야함
		List<Board> boardlist = service.boardlist(Map.of("cPage", cPage, "numPerpage", numPerpage));
		int totalData = service.selectBoardCount();
		//pageing
		model.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "/spring/board/boardlist.do"));
		model.addAttribute("totalData",totalData);
		model.addAttribute("boardlist", boardlist);
		return "board/boardlist";
	}
	
	@RequestMapping("/insertboard.do")
	public String insertboard(Board b) {
		
		return "board/insertboard";
	}
	@RequestMapping("/selectboard.do")
	public String selectboard(Model model, int no) {
		model.addAttribute("board", service.selectBoardByName(no));
		return "board/selectboard";
	}
}
