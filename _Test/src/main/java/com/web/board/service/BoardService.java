package com.web.board.service;

import java.sql.Connection;
import java.util.List;

import com.web.board.dao.BoardDao;
import com.web.board.dto.BoardDto;
import static com.web.common.JDBCTemplate.*;

public class BoardService {
	private BoardDao dao = new BoardDao();
	
	public List<BoardDto> BoardList(){
		Connection conn = getConnection();
		List<BoardDto> boardList = dao.BoardList(conn);
		close(conn);
		return boardList;
	}
	
	public BoardDto selectboardview(int number) {
		Connection conn = getConnection();
		BoardDto b = dao.selectboardview(conn, number);
		close(conn);
		return b;
	}
	public int insertboard(BoardDto b) {
		Connection conn = getConnection();
		int result = dao.insertboard(conn, b);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int updateboard(BoardDto b) {
		Connection conn = getConnection();
		int result = dao.updateboard(conn, b);
		close(conn);
		return result;
	}
	
	public int deleteboard(int number) {
		Connection conn = getConnection();
		int result = dao.deleteboard(conn, number);
		close(conn);
		return result;
	}
}
