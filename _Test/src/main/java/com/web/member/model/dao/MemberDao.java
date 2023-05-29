package com.web.member.model.dao;

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

import com.web.member.model.dto.MemberDto;

public class MemberDao {
	private Properties sql = new Properties();
	{
		String path = MemberDao.class.getResource("/sql/member/member.sql.properties").getPath();
		
		try {
			sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	private MemberDto getMember(ResultSet rs) throws SQLException {
		MemberDto m = new MemberDto();
		m.setUserId(rs.getString("userid"));
		m.setPassword(rs.getString("password"));
		m.setUserName(rs.getString("username"));
		m.setGender(rs.getString("gender").charAt(0));
		m.setAge(rs.getInt("age"));
		m.setEmail(rs.getString("email"));
		m.setPhone(rs.getString("phone"));
		m.setAddress(rs.getString("address"));
		m.setHobby(rs.getString("hobby").split(","));
		m.setEnrollDate(rs.getDate("enrolldate"));
		return m;
	}
	public MemberDto login(Connection conn, String userid, String password) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDto m = null;
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("login"));
			pstmt.setString(1, userid);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				m = getMember(rs);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;	
	}
	
	public int enrollerMember(Connection conn, MemberDto m) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("enrollMember"));
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, String.valueOf(m.getGender()));
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, String.join(",", m.getHobby()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public MemberDto duplicateid(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDto m = null;
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("duplicateid"));
			pstmt.setString(1, "userid");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m = getMember(rs);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
		
	}
	
	public List<MemberDto> selectMemberInfo(Connection conn, String loginId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberDto> list = new ArrayList<>();
		
		try {
			MemberDto m = new MemberDto();
			pstmt = conn.prepareStatement(sql.getProperty("selectMemberInfo"));
			
			pstmt.setString(1, loginId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				m = getMember(rs);
				
				list.add(m);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
		
	}
	public int updateMember(Connection conn, MemberDto m) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("updateMember"));
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());

			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
		
		
	}
	
}
