package com.web.admin.dao;

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

import oracle.jdbc.proxy.annotation.Pre;

import static com.web.member.model.dao.MemberDao.*;

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
	public List<MemberDto> membermanagement(Connection conn, int cPage, int numPerpage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberDto> memberinfo = new ArrayList<>();
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
	public List<MemberDto> searchMemberList(Connection conn, String keyword, int index) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberDto> m = new ArrayList<>();
		try {
			if(index == 0) {
				pstmt = conn.prepareStatement(sql.getProperty("searchMemberId"));
				pstmt.setString(1, "%"+keyword+"%");
			}else if(index == 1) {
				pstmt = conn.prepareStatement(sql.getProperty("searchMemberName"));
				pstmt.setString(1, "%"+keyword+"%");
			}else if(index == 2) {
				pstmt = conn.prepareStatement(sql.getProperty("searchMemberGender"));
				pstmt.setString(1, "%"+keyword+"%");
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				m.add(getMember(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
	}
	
	
}
