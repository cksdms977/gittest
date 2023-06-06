package com.web.admin.service;

import static com.web.common.JDBCTemplate.close;
import static com.web.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.web.admin.dao.AdminMemberDao;
import com.web.member.model.dto.MemberDto;


public class AdminMemberService {
	private AdminMemberDao dao = new AdminMemberDao();

	
	public List<MemberDto> membermanagement(int cPage, int numPerpage ) {
		Connection conn = getConnection();
		List<MemberDto> list = dao.membermanagement(conn, cPage, numPerpage);
		close(conn);
		return list;
	}
	public int selectMemberCount() {
		Connection conn = getConnection();
		int result = dao.selectMemberCount(conn);
		close(conn);
		return result;
	}
	
	public List<MemberDto> searchMemberList(String keyword, int index) {
		Connection conn = getConnection();
		List<MemberDto> m = dao.searchMemberList(conn, keyword, index);
		close(conn);
		return m;
	}
}
