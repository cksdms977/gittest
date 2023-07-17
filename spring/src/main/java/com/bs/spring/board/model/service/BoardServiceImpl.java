package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.board.dao.BoardDao;
import com.bs.spring.board.model.dto.Board;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao dao; 
	@Autowired
	private SqlSessionTemplate session;

	

	@Override
	public int insertBoard(Board b) {
		return 0;
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
