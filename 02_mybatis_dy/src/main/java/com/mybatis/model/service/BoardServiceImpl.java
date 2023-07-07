package com.mybatis.model.service;

import org.apache.ibatis.session.SqlSession;

import com.mybatis.common.SessionTemplate;
import com.mybatis.model.dao.BoardDao;
import com.mybatis.model.dao.BoardDaoImpl;
import com.mybatis.model.vo.Board;

public class BoardServiceImpl implements BoardService {
	private BoardDao dao = new BoardDaoImpl();
	@Override
	public Board selectBoard(int no) {
		SqlSession session = SessionTemplate.getWebSession();
		Board board = dao.selectBoard(session, no);
		session.close();
		return board;
	}

}
