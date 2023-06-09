package com.web.board.service;

import java.sql.Connection;
import java.util.List;

import com.web.board.dao.BoardComment;
import com.web.board.dao.BoardDao;
import com.web.board.dto.BoardDto;
import static com.web.common.JDBCTemplate.*;

public class BoardService {
	private BoardDao dao = new BoardDao();
	
	public List<BoardDto> BoardList(int cPage, int numPerpage){
		Connection conn = getConnection();
		List<BoardDto> boardList = dao.BoardList(conn, cPage, numPerpage);
		close(conn);
		return boardList;
	}
	
	public int selectBoardCount() {
		Connection conn = getConnection();
		int result = dao.selectBoardCount(conn);
		close(conn);
		return result;
	}
	
	public BoardDto selectboardview(int number, boolean isRead) {
		Connection conn = getConnection();
		BoardDto b = dao.selectboardview(conn, number);
		if(b != null && isRead) {
			int result = dao.updateBoardReadCount(conn,number);
			if(result > 0) {
				commit(conn);
				b.setBoardReadcount(b.getBoardReadcount() + 1);
			}
			else rollback(conn);
		}
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
	public int insertboardComment(BoardComment bc) {
		Connection conn = getConnection();
		int result = dao.insertboardComment(conn, bc);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	public List<BoardComment> selectBoardComment(int number){
		Connection conn = getConnection();
		List<BoardComment> list = dao.selectBoardComment(conn, number);
		close(conn);
		return list;
	}	
}
