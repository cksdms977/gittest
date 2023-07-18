package com.bs.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;

@Repository
public class BoardDaoImpl implements BoardDao {


	@Override
	public int insertBoard(SqlSessionTemplate session, Board b) {
		return session.insert("board.insertboard", b);
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

	@Override
	public int insertAttachment(SqlSessionTemplate session, Attachment a) {
		return session.insert("board.insertattachment", a);
	}

}
