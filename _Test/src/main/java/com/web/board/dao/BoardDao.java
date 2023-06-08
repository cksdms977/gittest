package com.web.board.dao;

import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.catalina.filters.RestCsrfPreventionFilter;

import static com.web.common.JDBCTemplate.*;
import com.web.board.dto.BoardDto;

import oracle.jdbc.proxy.annotation.Pre;

public class BoardDao {
	private Properties sql = new Properties();
	{
		String path = BoardDao.class.getResource("/sql/board/board.sql.properties").getPath();
		try {
			sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static BoardDto getBoard(ResultSet rs) throws SQLException {
		return BoardDto.builder()
				.boardNo(rs.getInt("board_no"))
				.boardTitle(rs.getString("board_title"))
				.boardWriter(rs.getString("board_writer"))
				.boardContent(rs.getString("board_content"))
				.boardOriginal(rs.getString("board_original_filename") != null ? rs.getString("board_original_filename") : null)
				.boardRenamed(rs.getString("board_renamed_filename") != null ? rs.getString("board_renamed_filename") : null)
				.boardDate(rs.getDate("board_date"))
				.boardReadcount(rs.getInt("board_readcount")).build();
	}
	
	public List<BoardDto> BoardList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardDto> boardList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("BoardList"));
//			pstmt.setInt(1, (cPage-1) * numPerpage+1);
//			pstmt.setInt(2, cPage * numPerpage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				boardList.add(getBoard(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return boardList;
	}
	
	
	
	public BoardDto selectboardview(Connection conn, int number) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDto b = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectboardview"));
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				b = getBoard(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return b;
		
	}
	
	public int insertboard(Connection conn, BoardDto b) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertboard"));
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardWriter());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getBoardOriginal() != null ? b.getBoardOriginal() : null);
			pstmt.setString(5, b.getBoardRenamed() != null ? b.getBoardRenamed() : null);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int updateboard(Connection conn, BoardDto b) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("updateboard"));
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardWriter());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getBoardRenamed() != null ? b.getBoardRenamed() : null);

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int deleteboard(Connection conn, int number) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("deleteboard"));
			pstmt.setInt(1, number);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
}
