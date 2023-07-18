package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bs.spring.board.dao.BoardDao;
import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao dao; 
	@Autowired
	private SqlSessionTemplate session;

	

	@Override
	@Transactional 
	public int insertBoard(Board b) {
		// 2개의 insert문 실행!!
		log.debug("실행전 : {}", b.getBoardNo());
		int result = dao.insertBoard(session, b);
		log.debug("실행후 : {}", b.getBoardNo());
		if(result > 0) {
			if (b.getFile().size() > 0) {
				for (Attachment a : b.getFile()) {
					a.setBoardNo(b.getBoardNo());
					result += dao.insertAttachment(session, a);
//					result = dao.insertAttachment(session, a);
//					if(result != 1) throw new RuntimeException("첨부파일의 내용이 이상합니다.!!");
				}
			}
		}
		//rollback처리를 원하다면..... RuntimeException을 발생시키면됨.
		if(result!= b.getFile().size()+1) throw new RuntimeException("첨부파일의 내용이 이상합니다.!!");
		
		return result;
	}

	@Override
	public int selectBoardCount() {
		return dao.selectBoardCount(session);
	}

	@Override
	public Board selectBoardByName(int no) {
		return dao.selectBoardByName(session, no);
	}

	@Override
	public List<Board> boardlist(Map<String, Object> param) {
		return dao.boardlist(session, param);
	}

}
