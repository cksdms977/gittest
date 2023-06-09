package com.web.notice.dao;

import static com.web.common.JDBCTemplate.close;
import static com.web.member.model.dao.MemberDao.getMember;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.web.admin.dao.AdminMemberDao;
import com.web.member.model.dto.MemberDto;
import com.web.notice.dto.NoticeDto;

import oracle.jdbc.proxy.annotation.Pre;

public class NoticeDao {
	private Properties sql = new Properties();
	{
		String path = AdminMemberDao.class.getResource("/sql/notice/notice.sql.properties").getPath();
		try {
			sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static NoticeDto getNotice(ResultSet rs) throws SQLException {
		return NoticeDto.builder()
				.noticeNo(rs.getInt("notice_no"))
				.noticeTitle(rs.getString("notice_title"))
				.noticeWriter(rs.getString("notice_writer"))
				.noticeContent(rs.getString("notice_content"))
				.noticeDate(rs.getDate("notice_date"))
				.noticefilePath(rs.getString("filepath") != null ? rs.getString("filepath") : null)
				.status(rs.getString("status").charAt(0))
				.build();
	}
	
	public List<NoticeDto> Noticeinfo(Connection conn, int cPage, int numPerpage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<NoticeDto> noticeinfo = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("noticeinfo"));
			pstmt.setInt(1, (cPage-1) * numPerpage+1);
			pstmt.setInt(2, cPage * numPerpage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				noticeinfo.add(getNotice(rs));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return noticeinfo;
	}
	
	public int selectNoticeCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectNoticeCount"));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("RN");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	public int insertNotice(Connection conn, NoticeDto n) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertNotice"));
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeWriter());
			pstmt.setString(3, n.getNoticeContent());
			pstmt.setString(4, n.getNoticefilePath() != null ? n.getNoticefilePath() : null);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public NoticeDto selectnoticeview(Connection conn, int number) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeDto n = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectnoticeview"));
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				n = getNotice(rs);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return n;
		
	}
	public int updatenotice(Connection conn, NoticeDto n, int number) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("updatenotice"));
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeWriter());
			pstmt.setString(3, n.getNoticeContent());
			pstmt.setString(4, n.getNoticefilePath() != null ? n.getNoticefilePath() : null);
			
			
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int deletenotice(Connection conn, int number) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("deletenotice"));
			pstmt.setInt(1, number);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	
}
