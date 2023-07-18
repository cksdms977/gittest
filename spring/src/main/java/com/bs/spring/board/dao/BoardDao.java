package com.bs.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;

public interface BoardDao {
	int insertBoard(SqlSessionTemplate session, Board b);
	List<Board> boardlist(SqlSessionTemplate session, Map<String, Object> param);
	int selectBoardCount(SqlSessionTemplate session);
	Board selectBoardByName(SqlSessionTemplate session, int no);
	int insertAttachment(SqlSessionTemplate session, Attachment a);
}
