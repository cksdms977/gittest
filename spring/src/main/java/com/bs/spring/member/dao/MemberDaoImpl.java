package com.bs.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.member.model.dto.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Override
	public int insertMember(SqlSessionTemplate session, Member m) {
		return session.insert("member.insertMember", m);
	}

	@Override
	public Member selectMemberById(SqlSessionTemplate session, Map<String, Object> param) {
		return session.selectOne("member.selectById", param);
	}

	@Override
	public List<Member> memberlist(SqlSessionTemplate session) {
		return session.selectList("member.memberlist");
	}
	

//	@Override
//	public List<Member> mypage(SqlSessionTemplate session, String userId) {
//		return session.selectList("member.mypage", userId);
//	}
}
