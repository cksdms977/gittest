package com.web.board.dao;

import static com.web.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
	
	public List<BoardDto> BoardList(Connection conn, int cPage, int numPerpage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardDto> boardList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("BoardList"));
			pstmt.setInt(1, (cPage-1) * numPerpage+1);
			pstmt.setInt(2, cPage * numPerpage);
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
	
	public int selectBoardCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectBoardCount"));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
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
	public int updateBoardReadCount(Connection conn, int number) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("updateBoardReadCount"));
			pstmt.setInt(1, number);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
		
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
	
	public int insertboardComment(Connection conn, BoardComment bc) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertboardComment"));
			pstmt.setInt(1, bc.getLevel());
			pstmt.setString(2, bc.getBoardCommentWriter());
			pstmt.setString(3, bc.getBoardCommentContent());
			pstmt.setInt(4, bc.getBoardRef());
			pstmt.setString(5, bc.getBoardCommentRef() == 0 ? null : String.valueOf(bc.getBoardCommentRef()));
			
			result = pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public List<BoardComment> selectBoardComment(Connection conn, int number) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardComment> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectBoardComment"));
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(getBoardComment(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	private BoardComment getBoardComment(ResultSet rs) throws SQLException {
		return BoardComment.builder()
				.boardCommentNo(rs.getInt("board_comment_no"))
				.level(rs.getInt("board_comment_level"))
				.boardCommentWriter(rs.getString("board_comment_writer"))
				.boardCommentContent(rs.getString("board_comment_content"))
				.boardCommentDate(rs.getDate("board_comment_date"))
				.boardCommentRef(rs.getInt("board_comment_ref"))
				.boardRef(rs.getInt("board_ref"))
				.build();
	}
	
}
