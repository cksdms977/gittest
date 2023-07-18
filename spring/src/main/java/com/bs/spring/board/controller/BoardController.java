package com.bs.spring.board.controller;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bs.spring.board.model.dto.Attachment;
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
	
	@RequestMapping("/boardform.do")
	public String boardform() {
		
		return "board/insertboard";
	}
	
	@RequestMapping("/insertboard.do")
	public String insertboard(Board b, MultipartFile[] upFile, HttpSession session, Model model) {
		log.info("{}", b);
		log.info("{}", upFile);
		// MultipartFile에서 제공하는 메소드를 이용해서
		// 파일을 저장 할 수 있음 -> transferTo()메소드를 이용
		// 절대경로 가져오기
		String path = session.getServletContext().getRealPath("/resources/upload/board/");
		// 파일명에 대한 renamed 규칙을 설정
		// 직접 rename규칙을 만들어서 저장해보자
		// yyyyMMdd_HHmmssSSS_랜덤값
//		List<Attachment> files = new ArrayList<>();
		if(upFile!=null) {
			for(MultipartFile mf : upFile) {
				if(!mf.isEmpty()) {
					String oriName = mf.getOriginalFilename();
					String ext = oriName.substring(oriName.lastIndexOf("."));
					Date today = new Date(System.currentTimeMillis());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
					int rdn = (int) (Math.random() * 10000) + 1;
					String rename = sdf.format(today) + "_" + rdn + ext;

					try {
						mf.transferTo(new File(path + rename));
					} catch (IOException e) {
						e.printStackTrace();
					}
					Attachment file = Attachment.builder().originalFilename(oriName)
							.renamedFilename(rename).build();
					b.getFile().add(file);
				}
			}
		}
		try {
			service.insertBoard(b);
		} catch(RuntimeException e){
			for(Attachment a : b.getFile()) {
				File delFile = new File(path + a.getRenamedFilename());
				delFile.delete();
			}
			model.addAttribute("msg", "글쓰기 등록실패! :P");
			model.addAttribute("loc", "/board/boardform.do");
			return "common/msg";
		}
		return "redirect:/board/boardlist.do";
	}
	@RequestMapping("/selectboard.do")
	public String selectboard(Model model, int no) {
		model.addAttribute("board", service.selectBoardByName(no));
		return "board/selectboard";
	}
	
	@RequestMapping("/filedownload")
	public void fileDown(String oriname, String rename, OutputStream out, 
			@RequestHeader(value="user-agent") String header, HttpSession session, HttpServletResponse res) {
	
		String path = session.getServletContext().getRealPath("/resources/upload/board/");
		File downloFile = new File(path + rename);
		try(FileInputStream fis = new FileInputStream(downloFile);
				BufferedInputStream bis = new BufferedInputStream(fis);
				BufferedOutputStream bos = new BufferedOutputStream(out)) {
			boolean isMs = header.contains("Trident") || header.contains("MSIE");
			String ecodeRename = "";
			if(isMs) {
				ecodeRename = URLEncoder.encode(oriname, "UTF-8");
				ecodeRename = ecodeRename.replaceAll("\\+","%20");
			}else {
				ecodeRename = new String(oriname.getBytes("UTF-8"), "IOS-8859-1");
			}
			
			res.setContentType("application/octet-stream;charset=utf-8");
			res.setHeader("Content-Disposition", "attachment;filename=\""+ecodeRename+"\"");
			
			int read = -1;
			while((read=bis.read()) != -1) {
				bos.write(read);
			}
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
}
