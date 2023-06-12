package com.web.notice.service;

import static com.web.common.JDBCTemplate.close;
import static com.web.common.JDBCTemplate.getConnection;
import static com.web.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.List;

import com.web.member.model.dto.MemberDto;
import com.web.notice.dao.NoticeDao;
import com.web.notice.dto.NoticeDto;

public class NoticeService {
	private NoticeDao dao = new NoticeDao();
	
	public List<NoticeDto> Noticeinfo(int cPage, int numPerpage) {
		Connection conn = getConnection();
		List<NoticeDto> noticeinfo = dao.Noticeinfo(conn, cPage, numPerpage);
		close(conn);
		return noticeinfo;
	}
	public int selectNoticeCount() {
		Connection conn = getConnection();
		int result = dao.selectNoticeCount(conn);
		close(conn);
		return result;
	}
	
	public int insertNotice(NoticeDto n) {
		Connection conn = getConnection();
		int result = dao.insertNotice(conn, n);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public NoticeDto selectnoticeview(int number) {
		Connection conn = getConnection();
		NoticeDto n = dao.selectnoticeview(conn, number);
		close(conn);
		return n;
	}
	
	public int updatenotice(NoticeDto n, int number) {
		Connection conn = getConnection();
		int result = dao.updatenotice(conn, n, number);
		close(conn);
		return result;
	}
	
	public int deletenotice(int number) {
		Connection conn = getConnection();
		int result = dao.deletenotice(conn, number);
		close(conn);
		return result;
	}
}
