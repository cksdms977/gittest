package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.board.model.dto.Board;

public interface BoardService {
	int insertBoard(Board b);
	List<Board> boardlist(Map<String, Object> param);
	int selectBoardCount();
	Board selectBoardByName(int no);
}
