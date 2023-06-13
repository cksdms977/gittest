package com.web.admin.model.service;

import java.awt.dnd.DropTargetContext;

import java.sql.Connection;
import java.util.List;
import static com.web.common.JDBCTemplate.*;
import com.web.admin.model.dao.AdminMemberDao;
import com.web.member.model.vo.Member;

public class AdminMemberSerivce {
	
private AdminMemberDao dao = new AdminMemberDao();

public List<Member> membermanagement(int cPage, int numPerpage ) {
	Connection conn = getConnection();
	List<Member> list = dao.membermanagement(conn, cPage, numPerpage);
	close(conn);
	return list;
}
public int selectMemberCount() {
	Connection conn = getConnection();
	int result = dao.selectMemberCount(conn);
	close(conn);
	return result;
}

public List<Member> selectMemberByKeyword(String keyword, String index, int ccPage, int numPerPage) {
	Connection conn = getConnection();
	List<Member> m = dao.selectMemberByKeyword(conn, keyword, index, ccPage, numPerPage);
	close(conn);
	return m;
}
public int selectMemberByKeywordCount(String type, String keyword) {
	Connection conn = getConnection();
	int count = dao.selectMemberByKeywordCount(conn, type, keyword);
	close(conn);
	return count;

}
	
}
