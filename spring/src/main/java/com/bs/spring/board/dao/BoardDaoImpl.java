package com.bs.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.board.model.dto.Board;

@Repository
public class BoardDaoImpl implements BoardDao {


	@Override
	public int insertBoard(SqlSessionTemplate session, Board b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectBoardCount(SqlSessionTemplate session) {
		return session.selectOne("board.boardCount");
	}

	@Override
	public Board selectBoardByName(SqlSessionTemplate session, int no) {
		return session.selectOne("board.selectboardbyname", no);
	}

	@Override
	public List<Board> boardlist(SqlSessionTemplate session, Map<String, Object> param) {
		int cPage = (int)param.get("cPage");
		int numPerpage = (int)param.get("numPerpage");
		RowBounds rb = new RowBounds((cPage-1)*numPerpage, numPerpage);
		return session.selectList("board.boardlist", null, rb);
	}

}
