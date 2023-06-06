package com.web.member.model.service;

import java.sql.Connection;
import java.util.List;

import com.web.member.model.dao.MemberDao;
import com.web.member.model.dto.MemberDto;
import static com.web.common.JDBCTemplate.*;

public class MemberSerivce {
	private MemberDao dao = new MemberDao();
	
	public MemberDto login(String userId, String password) {
		Connection conn = getConnection();
		MemberDto m = dao.login(conn, userId, password);
		close(conn);
		return m;
	}
	
	public int enrollerMember(MemberDto m) {
		Connection conn = getConnection();
		int result = dao.enrollerMember(conn, m);
		close(conn);
		return result;
	}
	
	public MemberDto duplicateid(String id) {
		Connection conn = getConnection();
		MemberDto m = dao.duplicateid(conn, id);
		close(conn);
		return m;
	}
	
	public List<MemberDto> selectMemberInfo(String loginId) {
		Connection conn = getConnection();
		List<MemberDto> list = dao.selectMemberInfo(conn, loginId);
		close(conn);
		return list;
	}
	
	public int updatemember(MemberDto updatemember) {
		Connection conn = getConnection();
		int m = dao.updateMember(conn, updatemember);
		close(conn);
		return m;
	}
	public int updatePaswword(String upserId, String password) {
		Connection conn = getConnection();
		int result = dao.updatePassword(conn, upserId, password);
		close(conn);
		return result;
	}
}	
