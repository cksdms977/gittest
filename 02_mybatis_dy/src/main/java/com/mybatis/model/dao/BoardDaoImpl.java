package com.mybatis.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.mybatis.model.vo.Board;

public class BoardDaoImpl implements BoardDao {
	@Override
	public Board selectBoard(SqlSession session, int no) {
		return session.selectOne("member.selectBoard", no);
	}
}
