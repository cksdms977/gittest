package com.web.admin.model.dao;

import static com.web.common.JDBCTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.spi.DirStateFactory.Result;

import com.web.member.model.dao.MemberDao;
import com.web.member.model.vo.Member;

import oracle.jdbc.proxy.annotation.Pre;
// 이렇게 import를 해주면 됨 
import static com.web.member.model.dao.MemberDao.getMember;
public class AdminMemberDao {
	private Properties sql = new Properties();
	
	{
		String path = AdminMemberDao.class.getResource("/sql/admin/adminsql.properties").getPath();
		try {
			sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Member> membermanagement(Connection conn, int cPage, int numPerpage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> memberinfo = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("membermanagement"));
			pstmt.setInt(1, (cPage-1) * numPerpage+1);
			pstmt.setInt(2, cPage * numPerpage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				memberinfo.add(getMember(rs));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return memberinfo;
		
	}
	public int selectMemberCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectMemberCount"));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1); // 컬럼이 하나이기때문에 getInt(1)을 쓰면됨 컬럼명에 자동으로 인덱스 번호가 부여되기때문에 지금 컬럼이 하나밖에 안되기때문에 이렇게 1이라고 쓸수있다. 
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
		
	}
	public List<Member> selectMemberByKeyword(Connection conn, String type, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = sql.getProperty("selectMemberByKeyword");
		query = query.replace("#COL", type);
		List<Member> members = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, type.equals("gender") ? keyword : "%" + keyword + "%");
			
			pstmt.setInt(2, (cPage-1) * numPerPage);
			pstmt.setInt(3, cPage * numPerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				members.add(getMember(rs));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return members;
	}
	
	public int selectMemberByKeywordCount(Connection conn, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String query = sql.getProperty("selectMemberByKeywordCount").replace("#COL", type);
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, type.equals("gender")?keyword:"%" + keyword + "%");
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
	
	
}
